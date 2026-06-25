package com.hotelmanagement.hotelmanagementbackend.service;

import com.hotelmanagement.hotelmanagementbackend.model.User;
import com.hotelmanagement.hotelmanagementbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register a new user
    public User registerUser(User user) {
        // Encrypt the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Default role if not provided
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("GUEST");
        }

        return userRepository.save(user);
    }

    // Login - verify email and password
    public boolean loginUser(String email, String rawPassword) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            return false; // user not found
        }

        User user = userOpt.get();
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    // Get user by id
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }
}