package com.example.JWTSpringDemo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class LoginRequestDto {
    private String username;

    private String password;
}
