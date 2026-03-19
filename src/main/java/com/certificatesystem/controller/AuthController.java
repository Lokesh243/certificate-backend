package com.certificatesystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.certificatesystem.entity.User;
import com.certificatesystem.repository.UserRepository;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User existing = userRepository
                .findByUsernameAndPassword(user.getUsername(), user.getPassword());

        if (existing != null) {
            return ResponseEntity.ok(existing);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}