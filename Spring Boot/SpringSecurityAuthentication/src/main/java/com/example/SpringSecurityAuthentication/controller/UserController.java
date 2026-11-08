package com.example.SpringSecurityAuthentication.controller;

import com.example.SpringSecurityAuthentication.dto.UserRegisterRequestDto;
import com.example.SpringSecurityAuthentication.dto.UserRegisterResponseDto;
import com.example.SpringSecurityAuthentication.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> register(
            @RequestBody UserRegisterRequestDto registerRequestDto
    ) {

        UserRegisterResponseDto userRegisterResponseDto
                = authService.register(registerRequestDto);

        return ResponseEntity.ok(userRegisterResponseDto);
    }

    @GetMapping("/homepage")
    public String home() {
        return "Welcome to the home page";
    }

}
