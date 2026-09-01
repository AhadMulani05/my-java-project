package com.example.helloworld;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {
    public String hello() {
        return "Hmm";
    }
}
