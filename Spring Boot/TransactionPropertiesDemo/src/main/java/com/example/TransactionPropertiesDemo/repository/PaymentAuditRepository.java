package com.example.TransactionPropertiesDemo.repository;

import com.example.TransactionPropertiesDemo.entity.PaymentAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentAuditRepository extends JpaRepository<PaymentAudit, Long> {

}
