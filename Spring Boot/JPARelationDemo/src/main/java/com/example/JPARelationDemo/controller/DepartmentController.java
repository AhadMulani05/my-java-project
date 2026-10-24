package com.example.JPARelationDemo.controller;

import com.example.JPARelationDemo.model.Department;
import com.example.JPARelationDemo.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/department")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<String> createDept(@RequestBody Department department) {
        departmentService.createDept(department);

        return ResponseEntity.ok("Done dept");
    }

    @PostMapping ("/withStudent")
    public ResponseEntity<String> createDept(@RequestBody Department department,
                                             @RequestParam String studentName) {
        departmentService.createDept(department, studentName);

        return ResponseEntity.ok("DONE");
    }

}
