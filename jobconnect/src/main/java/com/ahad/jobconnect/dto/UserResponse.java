package com.ahad.jobconnect.dto;

import com.ahad.jobconnect.entity.Role;

public record UserResponse(Long id, String name, String email, Role role) {
}
