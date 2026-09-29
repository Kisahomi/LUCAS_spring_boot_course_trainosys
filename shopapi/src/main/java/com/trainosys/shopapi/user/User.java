package com.trainosys.shopapi.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@RequiredArgsConstructor 
public class User {
    private final int id;
    private final String name;
    private final String email;
}
