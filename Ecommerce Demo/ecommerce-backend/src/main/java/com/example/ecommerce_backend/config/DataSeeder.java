package com.example.ecommerce_backend.config;

import com.example.ecommerce_backend.model.Category;
import com.example.ecommerce_backend.model.Product;
import com.example.ecommerce_backend.repository.CategoryRepository;
import com.example.ecommerce_backend.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public DataSeeder(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        if (productRepository.count() > 0) return;

        Category electronics = categoryRepository.save(new Category("Electronics"));
        Category fashion = categoryRepository.save(new Category("Fashion"));
        Category home = categoryRepository.save(new Category("Home & Kitchen"));
        Category books = categoryRepository.save(new Category("Books"));

        addProduct("Wireless Headphones", "Bluetooth over-ear headphones with deep bass and 30 hours battery.",
                "2499.00", 50, "https://picsum.photos/seed/headphones/400/300", electronics);
        addProduct("Smart Watch", "Fitness tracking, heart rate monitor, and notifications on your wrist.",
                "3999.00", 30, "https://picsum.photos/seed/smartwatch/400/300", electronics);
        addProduct("Bluetooth Speaker", "Portable waterproof speaker with clear sound.",
                "1799.00", 40, "https://picsum.photos/seed/speaker/400/300", electronics);
        addProduct("Men's Casual Shirt", "Soft cotton shirt, comfortable for everyday wear.",
                "799.00", 100, "https://picsum.photos/seed/shirt/400/300", fashion);
        addProduct("Running Shoes", "Lightweight shoes with strong grip and cushioned sole.",
                "1999.00", 60, "https://picsum.photos/seed/shoes/400/300", fashion);
        addProduct("Backpack", "Spacious backpack with laptop compartment.",
                "1299.00", 70, "https://picsum.photos/seed/backpack/400/300", fashion);
        addProduct("Non-stick Cookware Set", "5-piece non-stick set for daily cooking.",
                "2799.00", 25, "https://picsum.photos/seed/cookware/400/300", home);
        addProduct("Table Lamp", "Modern LED lamp with adjustable brightness.",
                "899.00", 45, "https://picsum.photos/seed/lamp/400/300", home);
        addProduct("Java Programming Book", "Learn Java from basics to advanced concepts.",
                "599.00", 80, "https://picsum.photos/seed/javabook/400/300", books);
        addProduct("Notebook Pack (5)", "Pack of five ruled notebooks.",
                "299.00", 150, "https://picsum.photos/seed/notebook/400/300", books);
    }

    private void addProduct(String name, String description, String price, int stock,
                            String imageUrl, Category category) {
        Product p = new Product();
        p.setName(name);
        p.setDescription(description);
        p.setPrice(new BigDecimal(price));
        p.setStock(stock);
        p.setImageUrl(imageUrl);
        p.setCategory(category);
        productRepository.save(p);
    }
}
