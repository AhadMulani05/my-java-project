package com.example.CrudSpringBootDemo.repository;

import com.example.CrudSpringBootDemo.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {
    public Student saveStudent(Student studentReq) {
        System.out.println("Inside the student repository");
        System.out.println("Existing the student repository");

        Student s1 = new Student();
        s1.setId(101);
        s1.setName("AhadMulani");
        s1.setEmail("ahadmulani@gamil.com");
        s1.setAge(21);
        s1.setSubject("Computer Science Boy");

        return s1;
    }
}
