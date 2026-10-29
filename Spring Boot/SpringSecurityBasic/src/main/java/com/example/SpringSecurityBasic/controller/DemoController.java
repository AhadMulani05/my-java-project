package com.example.SpringSecurityBasic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/demo")
public class DemoController {

    @GetMapping
    public ResponseEntity<String> getDemo() {
        return ResponseEntity.ok("Successfully getting something...");
    }

    @PostMapping
    public ResponseEntity<String> postDemo() {
        return ResponseEntity.ok("succesfullly we post the something with authorization...");
    }

}
