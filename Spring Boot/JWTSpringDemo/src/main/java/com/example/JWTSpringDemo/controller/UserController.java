package com.example.JWTSpringDemo.controller;

import com.example.JWTSpringDemo.dto.UserRegisterRequestDto;
import com.example.JWTSpringDemo.dto.UserRegisterResponseDto;
import com.example.JWTSpringDemo.service.AuthService;
import org.springframework.security.core.Authentication;
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
    public String sayHello(Authentication authentication) {
        return "Hello from the use name as: " + authentication.getName() ;
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
