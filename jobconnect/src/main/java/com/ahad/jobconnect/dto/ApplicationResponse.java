package com.ahad.jobconnect.dto;

import com.ahad.jobconnect.entity.ApplicationStatus;

import java.time.LocalDateTime;

public record ApplicationResponse(
        Long id,
        Long jobId,
        String jobTitle,
        String candidateName,
        String candidateEmail,
        ApplicationStatus status,
        String coverLetter,
        LocalDateTime appliedAt) {
}
