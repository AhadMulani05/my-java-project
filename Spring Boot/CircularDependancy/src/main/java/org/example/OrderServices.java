package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServices {

    @Autowired
    PaymentServices paymentservices;

    public void placeOrder() {
        paymentservices.pay();
        getDetails();
        System.out.println("Order Placed");
    }

    public void getDetails() {
        System.out.println("Order Detailed Done Boy...");
    }
}
