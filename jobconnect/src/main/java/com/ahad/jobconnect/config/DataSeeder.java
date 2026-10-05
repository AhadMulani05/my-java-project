package com.ahad.jobconnect.config;

import com.ahad.jobconnect.entity.Role;
import com.ahad.jobconnect.entity.User;
import com.ahad.jobconnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Creates a default ADMIN account on first start (admins cannot self-register).
 */
@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedAdmin(UserRepository users, PasswordEncoder encoder,
                                @Value("${app.admin.name}") String name,
                                @Value("${app.admin.email}") String email,
                                @Value("${app.admin.password}") String password) {
        return args -> {
            if (!users.existsByEmail(email)) {
                users.save(User.builder()
                        .name(name)
                        .email(email)
                        .password(encoder.encode(password))
                        .role(Role.ADMIN)
                        .build());
            }
        };
    }
}
