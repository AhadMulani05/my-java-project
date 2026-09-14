package com.example.SpringBootApplicationProperties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("paymentprop")
public class PaymentGateWay {
    private String name;
    private int retry;
    private int amount;

//    public PaymentGateWay(@Value("${PaymentGateWay.name}") String name,
//                          @Value("${PaymentGateWay.retry}") int retry,
//                          @Value("${PaymentGateWay.amount}")  int amount) {
//        this.name = name;
//        this.retry = retry;
//        this.amount = amount;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
