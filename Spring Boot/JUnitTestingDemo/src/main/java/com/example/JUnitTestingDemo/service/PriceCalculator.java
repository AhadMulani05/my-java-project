package com.example.JUnitTestingDemo.service;

import org.springframework.stereotype.Service;

@Service
public class PriceCalculator {

    public double calculatePrice(double price, double discount) {

        if(price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        if(discount < 0 || discount > 100) {
            throw new IllegalArgumentException(
                    "Discount should be in range 0 to 100"
            );
        }

        return price - ((price * discount)/100);
    }
}
