package com.ahad.jobconnect.service;

import com.ahad.jobconnect.dto.ApplicationResponse;
import com.ahad.jobconnect.dto.ApplyRequest;
import com.ahad.jobconnect.dto.PageResponse;
import com.ahad.jobconnect.entity.ApplicationStatus;
import com.ahad.jobconnect.entity.Job;
import com.ahad.jobconnect.entity.JobApplication;
import com.ahad.jobconnect.entity.Role;
import com.ahad.jobconnect.entity.User;
import com.ahad.jobconnect.exception.ConflictException;
import com.ahad.jobconnect.exception.ResourceNotFoundException;
import com.ahad.jobconnect.repository.JobApplicationRepository;
import com.ahad.jobconnect.repository.JobRepository;
import com.ahad.jobconnect.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final JobApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final CurrentUserService currentUserService;

    @Transactional
    public ApplicationResponse apply(Long jobId, ApplyRequest request) {
        User candidate = currentUserService.getCurrentUser();
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id " + jobId));

        if (!job.isActive()) {
            throw new ConflictException("This job is no longer accepting applications");
        }
        if (applicationRepository.existsByJobIdAndCandidateId(jobId, candidate.getId())) {
            throw new ConflictException("You have already applied to this job");
        }

        JobApplication application = JobApplication.builder()
                .job(job)
                .candidate(candidate)
                .status(ApplicationStatus.APPLIED)
                .coverLetter(request == null ? null : request.coverLetter())
                .build();
        return Mapper.toApplicationResponse(applicationRepository.save(application));
    }

    @Transactional(readOnly = true)
    public PageResponse<ApplicationResponse> myApplications(int page, int size) {
        User candidate = currentUserService.getCurrentUser();
        Pageable pageable = pageable(page, size);
        return PageResponse.from(applicationRepository
                .findByCandidateId(candidate.getId(), pageable)
                .map(Mapper::toApplicationResponse));
    }

    @Transactional(readOnly = true)
    public PageResponse<ApplicationResponse> applicationsForJob(Long jobId, int page, int size) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id " + jobId));
        checkOwnerOrAdmin(job, currentUserService.getCurrentUser());
        return PageResponse.from(applicationRepository
                .findByJobId(jobId, pageable(page, size))
                .map(Mapper::toApplicationResponse));
    }

    @Transactional
    public ApplicationResponse updateStatus(Long applicationId, ApplicationStatus status) {
        JobApplication application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id " + applicationId));
        checkOwnerOrAdmin(application.getJob(), currentUserService.getCurrentUser());
        application.setStatus(status);
        return Mapper.toApplicationResponse(applicationRepository.save(application));
    }

    private Pageable pageable(int page, int size) {
        return PageRequest.of(Math.max(page, 0), Math.min(Math.max(size, 1), 50), Sort.by(Sort.Direction.DESC, "appliedAt"));
    }

    private void checkOwnerOrAdmin(Job job, User user) {
        boolean isAdmin = user.getRole() == Role.ADMIN;
        boolean isOwner = job.getPostedBy().getId().equals(user.getId());
        if (!isAdmin && !isOwner) {
            throw new AccessDeniedException("You can only manage applications for your own jobs");
        }
    }
}
