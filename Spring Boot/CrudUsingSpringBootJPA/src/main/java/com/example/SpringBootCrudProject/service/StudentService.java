package com.example.SpringBootCrudProject.service;

import com.example.SpringBootCrudProject.repository.StudentRepository;
import org.springframework.stereotype.Service;
import com.example.SpringBootCrudProject.entity.Student;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student studentReq) {
        System.out.println("Inside student service");
        Student studentResp = studentRepository.saveStudent(studentReq);
        System.out.println("exiting student service");
        return studentResp;
    }
}
