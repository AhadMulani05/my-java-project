package com.example.AOPDemo.service;

import com.example.AOPDemo.dto.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public String createStudent() {

        System.out.println("Hi i am in service layer");

//        throw new RuntimeException("Some error Occured");

        return "Service layer";
    }

}
