package org.example;

import org.springframework.stereotype.Component;

@Component
public class PaymentServices {

//    OrderServices order = new OrderServices();

    public void pay() {
        System.out.println("payment is done");
//        order.getDetails();
    }
}
