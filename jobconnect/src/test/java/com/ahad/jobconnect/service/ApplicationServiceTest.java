package com.ahad.jobconnect.service;

import com.ahad.jobconnect.dto.ApplicationResponse;
import com.ahad.jobconnect.dto.ApplyRequest;
import com.ahad.jobconnect.entity.ApplicationStatus;
import com.ahad.jobconnect.entity.Job;
import com.ahad.jobconnect.entity.JobApplication;
import com.ahad.jobconnect.entity.Role;
import com.ahad.jobconnect.entity.User;
import com.ahad.jobconnect.exception.ConflictException;
import com.ahad.jobconnect.exception.ResourceNotFoundException;
import com.ahad.jobconnect.repository.JobApplicationRepository;
import com.ahad.jobconnect.repository.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {

    @Mock
    private JobApplicationRepository applicationRepository;
    @Mock
    private JobRepository jobRepository;
    @Mock
    private CurrentUserService currentUserService;
    @InjectMocks
    private ApplicationService applicationService;

    private User recruiter;
    private User candidate;
    private Job job;

    @BeforeEach
    void setUp() {
        recruiter = User.builder().id(1L).name("Recruiter").email("r@test.com").role(Role.RECRUITER).build();
        candidate = User.builder().id(2L).name("Candidate").email("c@test.com").role(Role.CANDIDATE).build();
        job = Job.builder().id(10L).title("Java Developer").description("d").location("Pune")
                .skills("Java").minExperience(0).active(true)
                .createdAt(LocalDateTime.now()).postedBy(recruiter).build();
    }

    @Test
    void apply_createsApplicationWithAppliedStatus() {
        when(currentUserService.getCurrentUser()).thenReturn(candidate);
        when(jobRepository.findById(10L)).thenReturn(Optional.of(job));
        when(applicationRepository.existsByJobIdAndCandidateId(10L, 2L)).thenReturn(false);
        when(applicationRepository.save(any(JobApplication.class))).thenAnswer(inv -> {
            JobApplication a = inv.getArgument(0);
            a.setId(100L);
            a.setAppliedAt(LocalDateTime.now());
            return a;
        });

        ApplicationResponse response = applicationService.apply(10L, new ApplyRequest("I love Java"));

        assertEquals(ApplicationStatus.APPLIED, response.status());
        assertEquals("Candidate", response.candidateName());
    }

    @Test
    void apply_twiceToSameJob_throwsConflict() {
        when(currentUserService.getCurrentUser()).thenReturn(candidate);
        when(jobRepository.findById(10L)).thenReturn(Optional.of(job));
        when(applicationRepository.existsByJobIdAndCandidateId(10L, 2L)).thenReturn(true);

        assertThrows(ConflictException.class, () -> applicationService.apply(10L, null));
        verify(applicationRepository, never()).save(any());
    }

    @Test
    void apply_toMissingJob_throwsNotFound() {
        when(currentUserService.getCurrentUser()).thenReturn(candidate);
        when(jobRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> applicationService.apply(99L, null));
    }

    @Test
    void updateStatus_byJobOwner_works() {
        JobApplication application = JobApplication.builder().id(100L).job(job).candidate(candidate)
                .status(ApplicationStatus.APPLIED).appliedAt(LocalDateTime.now()).build();
        when(applicationRepository.findById(100L)).thenReturn(Optional.of(application));
        when(currentUserService.getCurrentUser()).thenReturn(recruiter);
        when(applicationRepository.save(any(JobApplication.class))).thenAnswer(inv -> inv.getArgument(0));

        ApplicationResponse response = applicationService.updateStatus(100L, ApplicationStatus.SHORTLISTED);

        assertEquals(ApplicationStatus.SHORTLISTED, response.status());
    }

    @Test
    void updateStatus_byOtherRecruiter_throwsAccessDenied() {
        User stranger = User.builder().id(7L).name("Stranger").email("s@test.com").role(Role.RECRUITER).build();
        JobApplication application = JobApplication.builder().id(100L).job(job).candidate(candidate)
                .status(ApplicationStatus.APPLIED).appliedAt(LocalDateTime.now()).build();
        when(applicationRepository.findById(100L)).thenReturn(Optional.of(application));
        when(currentUserService.getCurrentUser()).thenReturn(stranger);

        assertThrows(AccessDeniedException.class,
                () -> applicationService.updateStatus(100L, ApplicationStatus.REJECTED));
    }
}
