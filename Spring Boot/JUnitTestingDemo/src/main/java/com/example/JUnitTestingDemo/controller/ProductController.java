package com.example.JUnitTestingDemo.controller;

import com.example.JUnitTestingDemo.entity.Product;
import com.example.JUnitTestingDemo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {

        Product product =
                productService.getProductById(id);

        return ResponseEntity.ok(product);

    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {

        Product saveProduct =
                productService.createProduct(product);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saveProduct);
    }

}
