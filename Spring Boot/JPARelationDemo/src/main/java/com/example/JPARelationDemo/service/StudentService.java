package com.example.JPARelationDemo.service;

import com.example.JPARelationDemo.model.Department;
import com.example.JPARelationDemo.model.Student;
import com.example.JPARelationDemo.repository.DepartmentRepository;
import com.example.JPARelationDemo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    private DepartmentRepository departmentRepository;

    public StudentService(StudentRepository studentRepository,
                          DepartmentRepository departmentRepository) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public void createStudent(Student student, Long deptId) {

        Department department = departmentRepository.getDepartmentById(deptId);

        student.setDepartment(department);
        department.getStudents().add(student);

        studentRepository.save(student);
    }

    @Transactional
    public void createStudent(Student student, String deptName) {
        Department department = new Department();
        department.setName(deptName);

        student.setDepartment(department);
        department.getStudents().add(student);

        departmentRepository.save(department);
        studentRepository.save(student);
    }

}
