package com.ahad.jobconnect.service;

import com.ahad.jobconnect.dto.JobRequest;
import com.ahad.jobconnect.dto.JobResponse;
import com.ahad.jobconnect.dto.PageResponse;
import com.ahad.jobconnect.entity.Job;
import com.ahad.jobconnect.entity.Role;
import com.ahad.jobconnect.entity.User;
import com.ahad.jobconnect.exception.BadRequestException;
import com.ahad.jobconnect.exception.ResourceNotFoundException;
import com.ahad.jobconnect.repository.JobRepository;
import com.ahad.jobconnect.repository.JobSpecifications;
import com.ahad.jobconnect.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class JobService {

    private static final Set<String> SORT_FIELDS = Set.of("createdAt", "title", "salary", "minExperience");

    private final JobRepository jobRepository;
    private final CurrentUserService currentUserService;

    @Transactional
    public JobResponse create(JobRequest request) {
        User recruiter = currentUserService.getCurrentUser();
        Job job = Job.builder()
                .title(request.title().trim())
                .description(request.description().trim())
                .location(request.location().trim())
                .skills(request.skills().trim())
                .minExperience(request.minExperience())
                .salary(request.salary())
                .active(true)
                .postedBy(recruiter)
                .build();
        return Mapper.toJobResponse(jobRepository.save(job));
    }

    @Transactional(readOnly = true)
    public PageResponse<JobResponse> search(String keyword, String location, String skill, Integer experience,
                                            int page, int size, String sortBy, String direction) {
        if (!SORT_FIELDS.contains(sortBy)) {
            throw new BadRequestException("sortBy must be one of " + SORT_FIELDS);
        }
        Sort.Direction dir = "asc".equalsIgnoreCase(direction) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(Math.max(page, 0), Math.min(Math.max(size, 1), 50), Sort.by(dir, sortBy));

        Page<JobResponse> result = jobRepository
                .findAll(JobSpecifications.search(keyword, location, skill, experience), pageable)
                .map(Mapper::toJobResponse);
        return PageResponse.from(result);
    }

    @Transactional(readOnly = true)
    public JobResponse getById(Long id) {
        return Mapper.toJobResponse(findJob(id));
    }

    @Transactional
    public JobResponse update(Long id, JobRequest request) {
        Job job = findJob(id);
        checkOwnerOrAdmin(job, currentUserService.getCurrentUser());
        job.setTitle(request.title().trim());
        job.setDescription(request.description().trim());
        job.setLocation(request.location().trim());
        job.setSkills(request.skills().trim());
        job.setMinExperience(request.minExperience());
        job.setSalary(request.salary());
        return Mapper.toJobResponse(jobRepository.save(job));
    }

    @Transactional
    public void delete(Long id) {
        Job job = findJob(id);
        checkOwnerOrAdmin(job, currentUserService.getCurrentUser());
        jobRepository.delete(job);
    }

    private Job findJob(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id " + id));
    }

    private void checkOwnerOrAdmin(Job job, User user) {
        boolean isAdmin = user.getRole() == Role.ADMIN;
        boolean isOwner = job.getPostedBy().getId().equals(user.getId());
        if (!isAdmin && !isOwner) {
            throw new AccessDeniedException("You can only modify your own jobs");
        }
    }
}
