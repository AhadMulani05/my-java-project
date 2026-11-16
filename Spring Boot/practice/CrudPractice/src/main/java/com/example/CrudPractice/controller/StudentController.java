package com.example.CrudPractice.controller;

import com.example.CrudPractice.entity.Student;
import com.example.CrudPractice.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<Student>> findAllStudent() {
         List<Student> students = studentService.findAllStudent();

        return ResponseEntity.ok(students);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Student> findStudentById(@PathVariable Long id) {
        Optional<Student> students = studentService.findStudentById(id);

        if(students.isPresent()) {
            return ResponseEntity.ok(students.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);

        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);

        return ResponseEntity.ok("Deleting succesfully");
    }

}
