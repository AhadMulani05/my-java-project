package org.example;

import org.example.Payments.PaymentInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component
public class OrderServices {

    PaymentInterface payment;

    public OrderServices(PaymentInterface payment) {
        this.payment = payment;
    }

    public void orderDisplay() {

        payment.pay();
        System.out.println("order is done ok");
    }
}
