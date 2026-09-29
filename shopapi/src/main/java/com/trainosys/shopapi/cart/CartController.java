package com.trainosys.shopapi.cart;

import org.springframework.web.bind.annotation.RestController;

import com.trainosys.shopapi.product.Product;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@RestController
@RequestMapping("/api/carts")
public class CartController {
    @GetMapping("/{userId}")
    public CartItem getMethodName(@PathVariable  int userId) {
        return new CartItem(1, 12);
    }
    @GetMapping("/{userId}/total")
    public int getTotal(@PathVariable  int userId) {
        return 100;
    }
    @PostMapping("/{userId}/items")
    public Product postMethodName(@PathVariable  int userId, @RequestBody Product entity) {
        //TODO: process POST request
        
        return entity;
    }
    
    @PutMapping("{userId}/items/{productId}")
    public CartItem putMethodName(@PathVariable int userId, @PathVariable int productId, @RequestBody int quantity) {
        //TODO: process PUT request
        
        return new CartItem(productId, quantity);
    }

    @DeleteMapping ("/{userId}")
    public String deleteMethodName(@PathVariable String userId) {
        //TODO: process PUT request
        
        return "Cleared the whole cart";
    }
    @DeleteMapping ("/{userId}/items/{productId}")
    public String deleteSpecific(@PathVariable int userId, @PathVariable int productId) {
        //TODO: process PUT request
        
        return "Removed the product with ID: " + productId;
    }
}
