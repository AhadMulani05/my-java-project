package com.example.TransactionPropertiesDemo.service;

import com.example.TransactionPropertiesDemo.entity.Orders;
import com.example.TransactionPropertiesDemo.entity.PaymentAudit;
import com.example.TransactionPropertiesDemo.repository.PaymentAuditRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentAuditService {

    private PaymentAuditRepository paymentAuditRepository;

    public PaymentAuditService(PaymentAuditRepository paymentAuditRepository) {
        this.paymentAuditRepository = paymentAuditRepository;
    }

    @Transactional
    public void audit(Orders order) {
        PaymentAudit paymentAudit = new PaymentAudit(order.getAmount(), order.getId(), true);

        paymentAuditRepository.save(paymentAudit);
    }

}
