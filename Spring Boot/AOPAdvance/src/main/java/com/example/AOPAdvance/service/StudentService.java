package com.example.AOPAdvance.service;

import com.example.AOPAdvance.annotation.TrackExecutionTime;
import com.example.AOPAdvance.dto.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @TrackExecutionTime
    public Student createStudent(Student student) {
        System.out.println("This is service layer");

        return student;
    }

    @TrackExecutionTime
    public String getStudent() {

        System.out.println("This is get Method from service");

        return "here is all details of students";
    }

}
