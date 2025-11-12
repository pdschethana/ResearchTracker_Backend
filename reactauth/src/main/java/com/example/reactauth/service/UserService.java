/*package com.example.reactauth.service;

import com.example.reactauth.model.User;
import com.example.reactauth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by username
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}*/

package com.example.reactauth.service;

import com.example.reactauth.model.User;
import com.example.reactauth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by username
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // ✅ NEW: Update user role
    public User updateUserRole(Long id, String newRole) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Validate role
        if (!isValidRole(newRole)) {
            throw new RuntimeException("Invalid role. Must be ADMIN, PI, or MEMBER");
        }

        user.setRole(newRole);
        return userRepository.save(user);
    }

    // ✅ NEW: Delete user
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

    // ✅ Helper method to validate roles
    private boolean isValidRole(String role) {
        return role != null &&
                (role.equals("ADMIN") || role.equals("PI") || role.equals("MEMBER"));
    }
}

