package com.trainosys.shopapi.controller;

import com.trainosys.shopapi.service.CartService;
import com.trainosys.shopapi.model.CartItem;
import com.trainosys.shopapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    @GetMapping("/public/carts/{userId}")
    public ResponseEntity<CartItem> getCartByUserId(@PathVariable Long userId) {
        CartItem cartItem = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cartItem);
    }

    @PostMapping("/public/carts/{userId}/items")
    public ResponseEntity addItemToCart(@PathVariable Long userId, @RequestBody CartItem item) {
        CartItem createdItem = cartService.addItemToCart(userId, item);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    @PutMapping("/public/carts/{userId}/items/{productId}")
    public ResponseEntity<CartItem> updateCartItemQuantity(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @RequestParam int quantity) {
        CartItem updatedItem = cartService.updateCartItemQuantity(userId, productId, quantity);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/public/carts/{userId}/items/{productId}")
    public ResponseEntity<CartItem> removeItemFromCart(
            @PathVariable Long userId,
            @PathVariable Long productId) {
        CartItem updatedItem = cartService.removeItemFromCart(userId,productId);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/public/carts/{userId}")
    public ResponseEntity<String> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok("Cart cleared successfully.");
    }

    @GetMapping("/admin/carts")
    public ResponseEntity<List<CartItem>> getAllCarts() {
        List<CartItem> carts = cartService.getAllCarts();
        return ResponseEntity.ok(carts);    }

    @GetMapping("/public/carts/{userId}/total")
    public ResponseEntity<Double> getCartTotal(@PathVariable Long userId) {
        double total = cartService.getCartTotal(userId);
        return ResponseEntity.ok(total);
    }
}
