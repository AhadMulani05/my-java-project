package com.ahad.jobconnect.dto;

import com.ahad.jobconnect.entity.Role;

public record AuthResponse(String token, String tokenType, String name, String email, Role role) {
}
