package com.trainosys.shopapi.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private double price;
    private String category;
    private int stock;
    
}
