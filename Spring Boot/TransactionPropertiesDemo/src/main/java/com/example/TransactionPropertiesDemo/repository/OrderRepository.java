package com.example.TransactionPropertiesDemo.repository;

import com.example.TransactionPropertiesDemo.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
