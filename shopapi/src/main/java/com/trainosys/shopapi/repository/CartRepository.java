package com.trainosys.shopapi.repository;

import com.trainosys.shopapi.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartItemIdAndUserId(Long cartItemId, Long userId);
    List<CartItem> findByUserId(Long userId);
    Optional<CartItem> findByUserIdAndProductId(Long userId, Long productId);
    void deleteByUserId(Long userId);
}
