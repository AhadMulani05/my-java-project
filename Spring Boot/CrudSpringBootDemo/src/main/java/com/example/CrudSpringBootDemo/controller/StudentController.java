package com.example.CrudSpringBootDemo.controller;

import com.example.CrudSpringBootDemo.entity.Student;
import com.example.CrudSpringBootDemo.service.StudentService;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println("Inside student controller");
        Student createdStudent = studentService.createStudent(student);
        System.out.println("Existing student controller");
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }
}

//create read update delete