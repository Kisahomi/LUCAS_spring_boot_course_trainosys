package com.trainosys.shopapi.user;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController 
@RequestMapping("/api/users")
public class UserController {

    @GetMapping
    public List<User> getAllUsers() {
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return new User(1,"test name","test email");
    }
    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return new User(1,"test name", email);
    }
    @PostMapping
    public User postMethodName(@RequestBody User entity) {
        //TODO: process POST request
        
        return entity;
    }
    @PutMapping("/{id}")
    public User putMethodName(@PathVariable int id, @RequestBody User user) {
        //TODO: process PUT request
        User userUpdate = new User(id, user.getName(), user.getEmail());
        return userUpdate;
    }
    @DeleteMapping("/{id}")
    public String deleteMethodName(@PathVariable int id) {
        //TODO: process PUT request
        
        return "Deleted User with ID: " + id;
    }
}
