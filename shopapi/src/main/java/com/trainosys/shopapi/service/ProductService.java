package com.trainosys.shopapi.service;

import com.trainosys.shopapi.model.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(@PathVariable Long productId);
    List<Product> getProductByCategory(@PathVariable String category);
    Product createProduct(@RequestBody Product product);
    Product updateProduct(@PathVariable Long productId, @RequestBody Product product);
    Product updateStock(@PathVariable Long productId, @PathVariable int quantity);
    String deleteProduct(@PathVariable Long productId);
}
