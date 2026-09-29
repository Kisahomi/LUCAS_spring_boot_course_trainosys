package com.trainosys.shopapi.cart;

import lombok.Setter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@Setter 
@RequiredArgsConstructor 
public class CartItem {
    private final int productId;
    private final int quantity;
}
