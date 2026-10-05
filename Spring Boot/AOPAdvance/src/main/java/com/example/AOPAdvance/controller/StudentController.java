package com.example.AOPAdvance.controller;

import com.example.AOPAdvance.dto.Student;
import com.example.AOPAdvance.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student student1 = studentService.createStudent(student);

        return ResponseEntity.ok(student1);
    }

    @GetMapping
    public String getStudent() {
        String student1 = studentService.getStudent();
        return student1;
    }

}
