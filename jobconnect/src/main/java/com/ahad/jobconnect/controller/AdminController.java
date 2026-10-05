package com.ahad.jobconnect.controller;

import com.ahad.jobconnect.dto.PageResponse;
import com.ahad.jobconnect.dto.UserResponse;
import com.ahad.jobconnect.repository.UserRepository;
import com.ahad.jobconnect.util.Mapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearerAuth")
public class AdminController {

    private final UserRepository userRepository;

    @GetMapping("/users")
    public PageResponse<UserResponse> users(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        return PageResponse.from(userRepository
                .findAll(PageRequest.of(Math.max(page, 0), Math.min(Math.max(size, 1), 50)))
                .map(Mapper::toUserResponse));
    }
}
