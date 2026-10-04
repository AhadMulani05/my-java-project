package com.example.AOPDemo.controller;

import com.example.AOPDemo.dto.Student;
import com.example.AOPDemo.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<String> createStudent() {
        String student1 = studentService.createStudent();

        System.out.println("student created finally");

        return ResponseEntity.ok(student1);
    }

}
