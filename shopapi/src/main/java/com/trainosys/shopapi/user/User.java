package com.trainosys.shopapi.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String email;
}
