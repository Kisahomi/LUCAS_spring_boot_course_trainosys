package com.trainosys.shopapi.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@RequiredArgsConstructor 
public class Product {
    private final int id;
    private final String name;
    private final double price;
    private final String category;
    private final int stock;
    
}
