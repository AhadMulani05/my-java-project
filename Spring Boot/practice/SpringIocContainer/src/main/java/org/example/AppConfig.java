package org.example;

import org.example.Payments.CardServices;
import org.example.Payments.UpiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@ComponentScan
public class AppConfig {

    @Bean
    public UpiServices upi() {
        return new UpiServices();
    }

    @Bean
    public CardServices card() {
        return new CardServices();
    }

    @Bean
    public OrderServices order(UpiServices upi) {
        return new OrderServices(upi);
    }

//    @Bean
//    public PaymentServices payment() {
//        return new PaymentServices();
//    }
//
//    @Bean
//    public OrderServices order(PaymentServices payment) {
//        return new OrderServices(payment);
//    }
}
