package com.trainosys.shopapi.service;

import com.trainosys.shopapi.model.CartItem;
import com.trainosys.shopapi.model.Product;
import com.trainosys.shopapi.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;
    @Override
    public CartItem getCartByUserId(Long userId) {

        return cartRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cart is not found"
                )
        );
    }

    @Override
    public CartItem addItemToCart(Long userId, CartItem item) {
        Optional<CartItem> existingItem = cartRepository
                .findByUserIdAndProductId(userId, item.getProductId());
        if (existingItem.isPresent()) {
            // Product exists in cart -> update quantity
            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
            return cartRepository.save(cartItem);
        } else {
            // Product is new -> assign userId and save
            item.setUserId(userId);
            return cartRepository.save(item);
        }
    }

    @Override
    public CartItem updateCartItemQuantity(Long userId, Long productId, int quantity) {
        CartItem cartItem = cartRepository.findByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cart item not found for user ID " + userId
                ));
        cartItem.setQuantity(quantity);


        return cartRepository.save(cartItem);
    }

    @Override
    public CartItem removeItemFromCart(Long userId, Long productId) {
        CartItem cartItem = cartRepository.findByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cart item not found for user ID " + userId
                ));
        return cartItem;
    }

    @Override
    public void clearCart(Long userId) {
        cartRepository.deleteByUserId(userId);
    }

    @Override
    public List<CartItem> getAllCarts() {

        return cartRepository.findAll();
    }

    @Override
    public double getCartTotal(Long userId) {
        List<CartItem> cartItems = cartRepository.findByUserId(userId);

        return cartItems.stream()
                .mapToDouble(item -> {
                    Product product = productService.getProductById(item.getProductId());
                    return product.getPrice() * item.getQuantity();
                })
                .sum();
    }
}
