package com.example.JPARelationDemo.repository;

import com.example.JPARelationDemo.model.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Department department) {
        entityManager.persist(department);
    }

    public Department getDepartmentById(Long id) {
        return entityManager.find(Department.class, id);
    }

}
