package com.ahad.jobconnect.dto;

import jakarta.validation.constraints.Size;

public record ApplyRequest(@Size(max = 2000, message = "Cover letter too long") String coverLetter) {
}
