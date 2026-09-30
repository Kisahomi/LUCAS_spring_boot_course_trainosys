package com.trainosys.shopapi.cart;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@Setter 
@AllArgsConstructor
public class CartItem {
    private Long productId;
    private int quantity;
}
