package com.example.Hibernate.service;

import com.example.Hibernate.model.Student;
import com.example.Hibernate.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void createStudent(Student student) {
        studentRepository.save(student);
    }

    @Transactional
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Transactional
    public Student findById(Long id) {
        return studentRepository.findById(id);
    }

    @Transactional
    public void updateStudent(Student student, Long id) {
        Student student1 = studentRepository.findById(id);

        if(student1 == null) {
            throw new RuntimeException("Student not found");
        }

        student1.setName(student.getName());
        student1.setEmail(student.getEmail());
        student1.setAge(student.getAge());
    }

    @Transactional
    public void deleteById(Long id) {
        Student student = studentRepository.findById(id);

        if(student == null) {
            throw new RuntimeException("student not found");
        }

        studentRepository.remove(student);
    }

}
