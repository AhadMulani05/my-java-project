package com.example.SpringBootCrudProject.contoller;

import com.example.SpringBootCrudProject.entity.Student;
import com.example.SpringBootCrudProject.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/students")
public class StudentContoller {

    private StudentService studentService;

    public StudentContoller(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println("Inside Student Controller");
        Student createdStudent = studentService.createStudent(student);
        System.out.println("Existing Student Controller");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    //create
    //update
    //delete
}
