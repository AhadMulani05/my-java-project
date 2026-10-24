package com.example.JPARelationDemo.service;

import com.example.JPARelationDemo.model.Department;
import com.example.JPARelationDemo.model.Student;
import com.example.JPARelationDemo.repository.DepartmentRepository;
import com.example.JPARelationDemo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    private StudentRepository studentRepository;

    public DepartmentService(DepartmentRepository departmentRepository,
                             StudentRepository studentRepository) {
        this.departmentRepository = departmentRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void createDept(Department department) {
        departmentRepository.save(department);
    }

    @Transactional
    public void createDept(Department department, String studentName) {
        Student student = new Student();
        student.setName(studentName);

        student.setDepartment(department);

        department.getStudents().add(student);

        studentRepository.save(student);
        departmentRepository.save(department);
    }

}
