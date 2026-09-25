package com.example.CRUDSpringJPAPractice.controller;

import com.example.CRUDSpringJPAPractice.entity.Student;
import com.example.CRUDSpringJPAPractice.service.StudentService;
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

    //create
    @PostMapping ("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);

    }

    //Read
    @GetMapping ("/get/{id}")
    public ResponseEntity<Student>getStudent(@PathVariable Long id) {

        Student studentResp =studentService.getStudent(id);

        if(studentResp == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentResp);

    }

    @GetMapping ("/getAll")
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> studentList = studentService.getAllStudent();

        if(studentList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.of(Optional.of(studentList));
    }

    //update
    @PutMapping ("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,
                                                 @RequestBody Student studentReq) {
        Student studentResp = studentService.updateStudent(id, studentReq);

        if(studentResp == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentResp);

    }

    //delete
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {

        boolean isDeleted = studentService.deleteStdent(id);

        if(!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Record Deleted");

    }

}
