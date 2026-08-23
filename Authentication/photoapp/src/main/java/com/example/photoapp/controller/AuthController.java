package com.example.photoapp.controller;

import com.example.photoapp.model.User;
import com.example.photoapp.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // ---- SIGNUP ----
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username and password are required"));
        }

        if (userRepository.findByUsername(username) != null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username already exists"));
        }

        // NOTE: plain text for simplicity in this demo. See "Security Note" at the bottom.
        User user = new User(username, password);
        userRepository.save(user);

        return ResponseEntity.ok(Map.of("message", "Signup successful"));
    }

    // ---- LOGIN ----
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body, HttpSession session) {
        String username = body.get("username");
        String password = body.get("password");

        User user = userRepository.findByUsername(username);

        if (user == null || !user.getPassword().equals(password)) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid username or password"));
        }

        session.setAttribute("loggedInUser", username);
        return ResponseEntity.ok(Map.of("message", "Login successful"));
    }

    // ---- CHECK SESSION (used by welcome.html to guard the page) ----
    @GetMapping("/session")
    public ResponseEntity<?> checkSession(HttpSession session) {
        String username = (String) session.getAttribute("loggedInUser");
        if (username == null) {
            return ResponseEntity.status(401).body(Map.of("message", "Not logged in"));
        }
        return ResponseEntity.ok(Map.of("username", username));
    }

    // ---- LOGOUT ----
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(Map.of("message", "Logged out"));
    }
}