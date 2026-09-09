package org.example;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Lazy
@Scope ("prototype")
public class OrderServices {

//    PaymentServices payment;

    public OrderServices() {
//        this.payment = payment;

        System.out.println("order constructor is here");
    }

    public void placeOrder() {
//        payment.pay();
        System.out.println("Order Done");
    }
}
