package com.trainosys.shopapi.service;



import com.trainosys.shopapi.model.User;
import com.trainosys.shopapi.repository.UserRepository;
import com.trainosys.shopapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {

        return userRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"
                )
        );
    }

    @Override
    public User getUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    @Override
    public User createUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User user) {
        User existingUser = getUserById(userId);

        if (user.getUserName() != null && !user.getUserName().isBlank()) {
            existingUser.setUserName(user.getUserName());
        }
        if (user.getEmail() != null && !user.getEmail().isBlank()) {
            existingUser.setEmail(user.getEmail());
        }
        return userRepository.save(existingUser);
    }

    @Override
    public String deleteUser(Long userId) {
        User existingUser = getUserById(userId);
        userRepository.delete(existingUser);
        return "User deleted successfully.";
    }
}
