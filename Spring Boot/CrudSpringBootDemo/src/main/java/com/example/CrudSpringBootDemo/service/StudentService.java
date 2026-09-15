package com.example.CrudSpringBootDemo.service;

import com.example.CrudSpringBootDemo.controller.StudentController;
import com.example.CrudSpringBootDemo.entity.Student;
import com.example.CrudSpringBootDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student studentReq) {
        System.out.println("Inside student service class");
        Student studentResp  = studentRepository.saveStudent(studentReq);
        System.out.println("Exiting student service");
        return studentReq;
    }
}
