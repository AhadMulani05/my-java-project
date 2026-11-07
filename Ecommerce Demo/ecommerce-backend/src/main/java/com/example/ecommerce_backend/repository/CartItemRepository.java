package com.example.ecommerce_backend.repository;

import com.example.ecommerce_backend.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Long userId);

    List<CartItem> findByProductId(Long productId);
    Optional<CartItem> findByUserIdAndProductId(Long userId, Long productId);
}
