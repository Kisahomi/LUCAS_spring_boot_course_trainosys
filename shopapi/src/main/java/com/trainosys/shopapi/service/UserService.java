package com.trainosys.shopapi.service;


import com.trainosys.shopapi.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(@PathVariable Long userId);
    User getUserByEmail(@PathVariable String email);
    User createUser(@RequestBody User user);
    User updateUser(@PathVariable Long userId, @RequestBody User user);
    String deleteUser(@PathVariable Long userId);
}
