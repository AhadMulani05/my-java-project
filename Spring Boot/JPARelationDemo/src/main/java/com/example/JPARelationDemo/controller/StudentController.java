package com.example.JPARelationDemo.controller;

import com.example.JPARelationDemo.model.Student;
import com.example.JPARelationDemo.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //create
    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student student,
                                                @RequestParam Long deptId) {
        studentService.createStudent(student, deptId);

        return ResponseEntity.ok("Done");
    }

    @PostMapping("/withDepartment")
    public ResponseEntity<String> createStudent (@RequestBody Student student,
                                                 @RequestParam String deptName) {
        studentService.createStudent(student, deptName);

        return ResponseEntity.ok("DONE");
    }

}
