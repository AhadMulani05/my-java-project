package com.example.CRUDSpringJPAPractice.service;

import com.example.CRUDSpringJPAPractice.entity.Student;
import com.example.CRUDSpringJPAPractice.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student studentReq) {
        Student studentResp = studentRepository.save(studentReq);

        return studentResp;
    }

    public Student getStudent(Long id) {

        Optional<Student> studentResp =studentRepository.findById(id);

        if(studentResp.isPresent()) {
            return studentResp.get();
        }

        return null;
    }

    public List<Student> getAllStudent () {
        List<Student> studentList = studentRepository.findAll();

        return studentList;
    }

    public Student updateStudent(Long id, Student studentReq) {

        Optional<Student> existingStudent = studentRepository.findById(id);

        if(existingStudent.isEmpty()) {
            return null;
        }

        Student studentToSave = existingStudent.get();

        studentToSave.setName(studentReq.getName());
        studentToSave.setEmail(studentReq.getEmail());
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setRollNo(studentReq.getRollNo());
        studentToSave.setSubject(studentReq.getSubject());

        return studentRepository.save(studentToSave);
    }

    public boolean deleteStdent(Long id) {
        Boolean isStudent = studentRepository.existsById(id);

        if(!isStudent) return false;

        studentRepository.deleteById(id);

        return true;
    }

}
