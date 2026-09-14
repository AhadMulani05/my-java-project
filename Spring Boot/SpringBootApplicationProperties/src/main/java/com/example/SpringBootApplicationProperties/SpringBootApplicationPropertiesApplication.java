package com.example.SpringBootApplicationProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootApplicationPropertiesApplication {

	public static void main(String[] args) {
		ApplicationContext context =  SpringApplication.run(SpringBootApplicationPropertiesApplication.class, args);

		PaymentGateWay payment = context.getBean(PaymentGateWay.class);

//		payment.setName("Ahad Rihaj Mulai");
//		payment.setRetry(5);
//		payment.setAmount(5000);

		System.out.println(payment.getName());
		System.out.println(payment.getRetry());
		System.out.println(payment.getAmount());
	}

}
