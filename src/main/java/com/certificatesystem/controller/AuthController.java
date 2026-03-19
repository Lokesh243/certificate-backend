package com.certificatesystem.controller;

import com.certificatesystem.entity.User;
import com.certificatesystem.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public User login(@RequestBody User user) {

        User existingUser = userRepository.findByUsernameAndPassword(
                user.getUsername(),
                user.getPassword()
        );

        if (existingUser != null) {
            return existingUser;   // ✅ IMPORTANT (returns role also)
        }

        return null;
    }
}