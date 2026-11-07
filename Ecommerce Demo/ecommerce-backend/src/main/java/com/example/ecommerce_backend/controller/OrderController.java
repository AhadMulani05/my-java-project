package com.example.ecommerce_backend.controller;

import com.example.ecommerce_backend.model.*;
import com.example.ecommerce_backend.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public OrderController(OrderRepository orderRepository,
                           CartItemRepository cartItemRepository,
                           ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    public record CheckoutRequest(Long userId, String shippingAddress) {}

    // Turn the user's cart into an order
    @PostMapping("/checkout")
    @Transactional
    public ResponseEntity<?> checkout(@RequestBody CheckoutRequest req) {
        List<CartItem> cartItems = cartItemRepository.findByUserId(req.userId());
        if (cartItems.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Your cart is empty"));
        }
        if (req.shippingAddress() == null || req.shippingAddress().isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Shipping address is required"));
        }

        // First check stock for everything, before changing anything
        for (CartItem ci : cartItems) {
            Product p = ci.getProduct();
            if (p.getStock() < ci.getQuantity()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("message", "Not enough stock for " + p.getName()));
            }
        }

        Order order = new Order();
        order.setUserId(req.userId());
        order.setShippingAddress(req.shippingAddress());
        order.setStatus("PLACED");
        order.setCreatedAt(LocalDateTime.now());

        BigDecimal total = BigDecimal.ZERO;
        for (CartItem ci : cartItems) {
            Product p = ci.getProduct();

            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setProductId(p.getId());
            oi.setProductName(p.getName());
            oi.setPrice(p.getPrice());
            oi.setQuantity(ci.getQuantity());
            order.getItems().add(oi);

            total = total.add(p.getPrice().multiply(BigDecimal.valueOf(ci.getQuantity())));

            p.setStock(p.getStock() - ci.getQuantity());
            productRepository.save(p);
        }
        order.setTotalAmount(total);

        Order saved = orderRepository.save(order);
        cartItemRepository.deleteAll(cartItems);
        return ResponseEntity.ok(saved);
    }

    // Order history of a user (newest first)
    @GetMapping("/user/{userId}")
    public List<Order> getOrders(@PathVariable Long userId) {
        return orderRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    // One order
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId) {
        return orderRepository.findById(orderId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
