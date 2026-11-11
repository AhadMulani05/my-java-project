package com.example.JUnitTestingDemo.service;

import com.example.JUnitTestingDemo.entity.Product;
import com.example.JUnitTestingDemo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(Long id) {

        return productRepository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("Product not found: "+ id));
    }

    public Product createProduct(Product product) {

        boolean alreadyExists =
                productRepository.existsByName(product.getName());

        if(alreadyExists) {
            throw new IllegalArgumentException(
                    "Product already exists"
            );
        }

        return productRepository.save(product);

    }

    public List<Product> findAllProduct() {

        return productRepository.findAll();
    }

}
