package com.example.FilterDemo.service;

import com.example.FilterDemo.dto.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public void createStudent(Student student) {
        System.out.println("Student Created");
        System.out.println(student.getName());
        System.out.println(student.getEmail());
    }

}
