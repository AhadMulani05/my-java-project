package com.example.SpringBootCrudProject.repository;

import com.example.SpringBootCrudProject.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    public Student saveStudent(Student studentReq) {
        System.out.println("inside the repository");
        System.out.println("exiting the repository");

        Student s1 = new Student();
        s1.setName("Ahad");
        s1.setAge(21);
        s1.setEmail("ahadmulani@gmail.com");
        s1.setRollNo(31);
        s1.setSubject("Computer Science");

        return s1;
    }
}
