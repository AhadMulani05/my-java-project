package com.example.JPADemo.service;

import com.example.JPADemo.entity.Student;
import com.example.JPADemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void createStudent(Student student) {
        studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        Optional<Student> student =  studentRepository.findById(id);

        if(student.isPresent()) {
            return student.get();
        }

        return null;
    }

    public List<Student> getAllStudent() {
        List<Student> students = studentRepository.findAll();

        return students;
    }

    public Student updateStudent(Student student, Long id) {
        Optional<Student> existingStudent = studentRepository.findById(id);

        if(existingStudent.isEmpty()) {
            return null;
        }

        Student studentToSave = existingStudent.get();

        studentToSave.setName(student.getName());
        studentToSave.setEmail(student.getEmail());
        studentToSave.setAge(student.getAge());

        return studentRepository.save(studentToSave);
    }

    public Boolean deleteStudentById(Long id) {
        Optional<Student> isStudent = studentRepository.findById(id);

        studentRepository.deleteById(id);

        return true;
    }

}
