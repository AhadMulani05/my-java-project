package com.example.SpringBootCoreDemo;

import org.springframework.stereotype.Component;

@Component
public class PaymentServices {
    public void pay() {
        System.out.println("Payment is done here now the processing...");
    }
}
