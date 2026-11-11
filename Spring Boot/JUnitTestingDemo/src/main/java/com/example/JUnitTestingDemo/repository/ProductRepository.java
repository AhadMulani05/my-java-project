package com.example.JUnitTestingDemo.repository;

import com.example.JUnitTestingDemo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);

}
