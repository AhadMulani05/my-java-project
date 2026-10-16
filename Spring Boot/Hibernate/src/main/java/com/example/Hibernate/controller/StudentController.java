package com.example.Hibernate.controller;

import com.example.Hibernate.model.Student;
import com.example.Hibernate.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        studentService.createStudent(student);

        return ResponseEntity.ok("Done");
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> students = studentService.getAllStudent();
        return ResponseEntity.ok(students);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Student> findStudent(@PathVariable Long id) {
        Student student = studentService.findById(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<String> updateStudent(@RequestBody Student student,
                                                 @PathVariable Long id) {
        studentService.updateStudent(student, id);
        return ResponseEntity.ok("Done");
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        studentService.deleteById(id);

        return ResponseEntity.ok("Done deleting");
    }

}
