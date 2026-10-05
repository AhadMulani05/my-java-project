package com.ahad.jobconnect.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record JobRequest(
        @NotBlank(message = "Title is required") @Size(max = 200) String title,
        @NotBlank(message = "Description is required") @Size(max = 4000) String description,
        @NotBlank(message = "Location is required") String location,
        @NotBlank(message = "Skills are required (comma separated)") String skills,
        @Min(value = 0, message = "Experience cannot be negative") int minExperience,
        @Positive(message = "Salary must be positive") Double salary) {
}
