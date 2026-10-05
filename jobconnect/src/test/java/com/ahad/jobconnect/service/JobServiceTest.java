package com.ahad.jobconnect.service;

import com.ahad.jobconnect.dto.JobRequest;
import com.ahad.jobconnect.dto.JobResponse;
import com.ahad.jobconnect.entity.Job;
import com.ahad.jobconnect.entity.Role;
import com.ahad.jobconnect.entity.User;
import com.ahad.jobconnect.exception.BadRequestException;
import com.ahad.jobconnect.exception.ResourceNotFoundException;
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
class JobServiceTest {

    @Mock
    private JobRepository jobRepository;
    @Mock
    private CurrentUserService currentUserService;
    @InjectMocks
    private JobService jobService;

    private User owner;
    private User otherRecruiter;
    private User admin;
    private Job job;

    @BeforeEach
    void setUp() {
        owner = User.builder().id(1L).name("Owner").email("owner@test.com").role(Role.RECRUITER).build();
        otherRecruiter = User.builder().id(2L).name("Other").email("other@test.com").role(Role.RECRUITER).build();
        admin = User.builder().id(3L).name("Admin").email("admin@test.com").role(Role.ADMIN).build();
        job = Job.builder().id(10L).title("Java Developer").description("Build APIs")
                .location("Pune").skills("Java,Spring Boot").minExperience(0)
                .active(true).createdAt(LocalDateTime.now()).postedBy(owner).build();
    }

    private JobRequest request() {
        return new JobRequest("Java Developer", "Build APIs", "Pune", "Java,Spring Boot", 0, 600000.0);
    }

    @Test
    void create_setsLoggedInRecruiterAsOwner() {
        when(currentUserService.getCurrentUser()).thenReturn(owner);
        when(jobRepository.save(any(Job.class))).thenAnswer(inv -> {
            Job saved = inv.getArgument(0);
            saved.setId(10L);
            saved.setCreatedAt(LocalDateTime.now());
            return saved;
        });

        JobResponse response = jobService.create(request());

        assertEquals(10L, response.id());
        assertEquals("Owner", response.postedBy());
        assertTrue(response.active());
    }

    @Test
    void getById_whenMissing_throwsNotFound() {
        when(jobRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> jobService.getById(99L));
    }

    @Test
    void update_byDifferentRecruiter_throwsAccessDenied() {
        when(jobRepository.findById(10L)).thenReturn(Optional.of(job));
        when(currentUserService.getCurrentUser()).thenReturn(otherRecruiter);

        assertThrows(AccessDeniedException.class, () -> jobService.update(10L, request()));
        verify(jobRepository, never()).save(any());
    }

    @Test
    void update_byOwner_savesChanges() {
        when(jobRepository.findById(10L)).thenReturn(Optional.of(job));
        when(currentUserService.getCurrentUser()).thenReturn(owner);
        when(jobRepository.save(any(Job.class))).thenAnswer(inv -> inv.getArgument(0));

        JobResponse response = jobService.update(10L,
                new JobRequest("Senior Java Dev", "Lead APIs", "Mumbai", "Java", 3, 900000.0));

        assertEquals("Senior Java Dev", response.title());
        assertEquals("Mumbai", response.location());
    }

    @Test
    void delete_byAdmin_isAllowed() {
        when(jobRepository.findById(10L)).thenReturn(Optional.of(job));
        when(currentUserService.getCurrentUser()).thenReturn(admin);

        jobService.delete(10L);

        verify(jobRepository).delete(job);
    }

    @Test
    void search_withInvalidSortField_throwsBadRequest() {
        assertThrows(BadRequestException.class,
                () -> jobService.search(null, null, null, null, 0, 10, "password", "asc"));
    }
}
