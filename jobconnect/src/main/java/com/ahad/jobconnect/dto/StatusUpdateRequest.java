package com.ahad.jobconnect.dto;

import com.ahad.jobconnect.entity.ApplicationStatus;
import jakarta.validation.constraints.NotNull;

public record StatusUpdateRequest(@NotNull(message = "Status is required") ApplicationStatus status) {
}
