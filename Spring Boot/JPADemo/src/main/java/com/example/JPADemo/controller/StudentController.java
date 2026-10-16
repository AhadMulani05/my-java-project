package com.example.JPADemo.controller;

import com.example.JPADemo.entity.Student;
import com.example.JPADemo.service.StudentService;
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

    //create
    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        studentService.createStudent(student);

        return ResponseEntity.ok("Done");
    }

    //read

    //get by id
    @GetMapping ("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student student1 = studentService.findStudent(id);

        return ResponseEntity.ok(student1);
    }

    //get all
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> students = studentService.getAllStudent();

        return ResponseEntity.ok(students);
    }

    //update
    @PutMapping ("/{id}")
    public ResponseEntity<String> updateStudent(@RequestBody Student student,
                                                @PathVariable Long id) {
        studentService.updateStudent(student, id);

        return ResponseEntity.ok("Student updated");
    }

    //delete
    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        boolean isDeleted = studentService.deleteStudentById(id);

        if(!isDeleted) return ResponseEntity.notFound().build();

        return ResponseEntity.ok("Deleted Successfully");
    }

}
