package com.ahad.jobconnect.dto;

import java.time.LocalDateTime;

public record JobResponse(
        Long id,
        String title,
        String description,
        String location,
        String skills,
        int minExperience,
        Double salary,
        boolean active,
        String postedBy,
        LocalDateTime createdAt) {
}
