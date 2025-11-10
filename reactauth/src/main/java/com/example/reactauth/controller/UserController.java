package com.example.reactauth.controller;

import com.example.reactauth.model.User;
import com.example.reactauth.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // allow requests from your frontend
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ✅ Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Optional: Get a user by username
    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getByUsername(username);
    }
}
