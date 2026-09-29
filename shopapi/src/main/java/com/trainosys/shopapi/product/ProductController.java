package com.trainosys.shopapi.product;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController 
@RequestMapping("/api/products")
public class ProductController {
    @GetMapping
    public List<Product> getAllProducts() {
        return new ArrayList<>();
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return new Product(1,"test name",1.0, "test category", 2);
    }
    @GetMapping("/category/{categoryId}")
    public List<Product> getProductByCategory(@PathVariable int categoryId) {
        return new ArrayList<>();
    }

    @PostMapping
    public Product postMethodName(@RequestBody Product entity) {
        //TODO: process POST request
        
        return entity;
    }
    @PutMapping("/{id}")
    public Product putMethodName(@PathVariable int id, 
        @RequestBody Product product
    ) {
        //TODO: process PUT request
        Product productUpdate = new Product(id, product.getName(), product.getPrice(), product.getCategory(), product.getStock());
        return productUpdate;
    }
    @PutMapping("/{id}/stock/{quantity}")
    public Product putStock(@PathVariable int id, @PathVariable int quantity,@RequestBody  Product product) {
        //Product product = getProductById(id); <--- this should work kaya lng walang actual stuff
        
        Product productUpdate = new Product(id, product.getName(), product.getPrice(), product.getCategory(), quantity);
        return productUpdate;
    }
    @DeleteMapping("/{id}")
    public String deleteMethodName(@PathVariable int id) {
        //TODO: process PUT request
        
        return "Deleted Product with ID: " + id;
    }
}
