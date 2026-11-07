package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.model.CartItem;
import com.example.ecommerce_backend.model.Product;
import com.example.ecommerce_backend.repository.CartItemRepository;
import com.example.ecommerce_backend.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartController(CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    public record AddToCartRequest(Long userId, Long productId, int quantity) {}

    // Get all cart items of a user
    @GetMapping("/{userId}")
    public List<CartItem> getCart(@PathVariable Long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    // Add a product to the cart (increases quantity if already in cart)
    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestBody AddToCartRequest req) {
        if (req.quantity() <= 0) {
            return ResponseEntity.badRequest().body(Map.of("message", "Quantity must be at least 1"));
        }
        Product product = productRepository.findById(req.productId()).orElse(null);
        if (product == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Product not found"));
        }

        CartItem item = cartItemRepository.findByUserIdAndProductId(req.userId(), req.productId())
                .orElseGet(() -> {
                    CartItem c = new CartItem();
                    c.setUserId(req.userId());
                    c.setProduct(product);
                    c.setQuantity(0);
                    return c;
                });

        int newQuantity = item.getQuantity() + req.quantity();
        if (newQuantity > product.getStock()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Only " + product.getStock() + " in stock"));
        }
        item.setQuantity(newQuantity);
        return ResponseEntity.ok(cartItemRepository.save(item));
    }

    // Change the quantity of one cart item
    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<?> updateQuantity(@PathVariable Long cartItemId, @RequestParam int quantity) {
        CartItem item = cartItemRepository.findById(cartItemId).orElse(null);
        if (item == null) {
            return ResponseEntity.notFound().build();
        }
        if (quantity <= 0) {
            cartItemRepository.delete(item);
            return ResponseEntity.noContent().build();
        }
        if (quantity > item.getProduct().getStock()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Only " + item.getProduct().getStock() + " in stock"));
        }
        item.setQuantity(quantity);
        return ResponseEntity.ok(cartItemRepository.save(item));
    }

    // Remove one item from the cart
    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<Void> removeItem(@PathVariable Long cartItemId) {
        if (!cartItemRepository.existsById(cartItemId)) {
            return ResponseEntity.notFound().build();
        }
        cartItemRepository.deleteById(cartItemId);
        return ResponseEntity.noContent().build();
    }

    // Empty the whole cart of a user
    @DeleteMapping("/clear/{userId}")
    @Transactional
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartItemRepository.deleteAll(cartItemRepository.findByUserId(userId));
        return ResponseEntity.noContent().build();
    }
}