package com.example.SpringBootCoreDemo;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderServices {
    PaymentServices paymentServices;

    public OrderServices(PaymentServices paymentServices) {
        this.paymentServices = paymentServices;
    }

    public void getOrder() {
        paymentServices.pay();
        System.out.println("order is complete");
    }
}
