package com.ahad.jobconnect.util;

import com.ahad.jobconnect.dto.ApplicationResponse;
import com.ahad.jobconnect.dto.JobResponse;
import com.ahad.jobconnect.dto.UserResponse;
import com.ahad.jobconnect.entity.Job;
import com.ahad.jobconnect.entity.JobApplication;
import com.ahad.jobconnect.entity.User;

/**
 * Entity -> DTO mapping so entities are never exposed through the API.
 */
public final class Mapper {

    private Mapper() {
    }

    public static JobResponse toJobResponse(Job job) {
        return new JobResponse(job.getId(), job.getTitle(), job.getDescription(), job.getLocation(),
                job.getSkills(), job.getMinExperience(), job.getSalary(), job.isActive(),
                job.getPostedBy().getName(), job.getCreatedAt());
    }

    public static ApplicationResponse toApplicationResponse(JobApplication a) {
        return new ApplicationResponse(a.getId(), a.getJob().getId(), a.getJob().getTitle(),
                a.getCandidate().getName(), a.getCandidate().getEmail(),
                a.getStatus(), a.getCoverLetter(), a.getAppliedAt());
    }

    public static UserResponse toUserResponse(User u) {
        return new UserResponse(u.getId(), u.getName(), u.getEmail(), u.getRole());
    }
}
