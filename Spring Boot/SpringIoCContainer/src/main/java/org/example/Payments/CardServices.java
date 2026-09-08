package org.example.Payments;

import org.springframework.stereotype.Component;

//@Component ("card")
public class CardServices implements PaymentInterface {

    public void pay() {
        System.out.println("payment via card");
    }
}
