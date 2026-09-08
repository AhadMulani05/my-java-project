package org.example.Payments;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Component ("upi")
public class UpiServices implements PaymentInterface{

    public void pay() {
        System.out.println("payment done using the upi");
    }
}
