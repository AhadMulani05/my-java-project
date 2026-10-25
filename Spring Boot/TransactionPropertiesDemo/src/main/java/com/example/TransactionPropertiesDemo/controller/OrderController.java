package com.example.TransactionPropertiesDemo.controller;

import com.example.TransactionPropertiesDemo.entity.Orders;
import com.example.TransactionPropertiesDemo.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody Orders orders) {
        orderService.placeOrder(orders);

        return ResponseEntity.ok("Done");
    }

}
