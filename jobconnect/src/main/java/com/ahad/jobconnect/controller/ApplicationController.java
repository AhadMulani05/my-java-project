package com.ahad.jobconnect.controller;

import com.ahad.jobconnect.dto.ApplicationResponse;
import com.ahad.jobconnect.dto.ApplyRequest;
import com.ahad.jobconnect.dto.PageResponse;
import com.ahad.jobconnect.dto.StatusUpdateRequest;
import com.ahad.jobconnect.service.ApplicationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/jobs/{jobId}/apply")
    @PreAuthorize("hasRole('CANDIDATE')")
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationResponse apply(@PathVariable Long jobId,
                                     @Valid @RequestBody(required = false) ApplyRequest request) {
        return applicationService.apply(jobId, request);
    }

    @GetMapping("/applications/me")
    @PreAuthorize("hasRole('CANDIDATE')")
    public PageResponse<ApplicationResponse> myApplications(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        return applicationService.myApplications(page, size);
    }

    @GetMapping("/jobs/{jobId}/applications")
    @PreAuthorize("hasAnyRole('RECRUITER','ADMIN')")
    public PageResponse<ApplicationResponse> applicationsForJob(@PathVariable Long jobId,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        return applicationService.applicationsForJob(jobId, page, size);
    }

    @PatchMapping("/applications/{id}/status")
    @PreAuthorize("hasAnyRole('RECRUITER','ADMIN')")
    public ApplicationResponse updateStatus(@PathVariable Long id,
                                            @Valid @RequestBody StatusUpdateRequest request) {
        return applicationService.updateStatus(id, request.status());
    }
}
