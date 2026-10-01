package com.trainosys.shopapi.service;

import com.trainosys.shopapi.model.CartItem;

import java.util.List;

public interface CartService {
    CartItem getCartByUserId(Long userId);
    CartItem addItemToCart(Long userId, CartItem item);
    CartItem updateCartItemQuantity(Long userId, Long cartItemId, int quantity);
    CartItem removeItemFromCart(Long userId, Long cartItemId);
    void clearCart(Long userId);
    List<CartItem> getAllCarts();
    double getCartTotal(Long userId);
}
