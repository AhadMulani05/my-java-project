package com.example.TransactionPropertiesDemo.service;

import com.example.TransactionPropertiesDemo.entity.Orders;
import com.example.TransactionPropertiesDemo.entity.PaymentAudit;
import com.example.TransactionPropertiesDemo.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private PaymentAuditService paymentAuditService;

    public OrderService(OrderRepository orderRepository, PaymentAuditService paymentAuditService) {
        this.orderRepository = orderRepository;
        this.paymentAuditService = paymentAuditService;
    }

    @Transactional
    public void placeOrder(Orders order) {
        orderRepository.save(order);

        paymentAuditService.audit(order);
    }

}
