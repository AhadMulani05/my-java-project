package org.example;

import jdk.jfr.Label;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Lazy
@Scope ("prototype")
public class PaymentServices {

    public PaymentServices() {
        System.out.println("Payment constructor is here");
    }

    public void pay() {
        System.out.println("Payment is Done hogya he");
    }
}
