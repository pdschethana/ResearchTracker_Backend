/*package com.example.reactauth.service;

import com.example.reactauth.model.User;
import com.example.reactauth.repository.UserRepository;
import com.example.reactauth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String register(String username, String password, String role) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        userRepository.save(user);
        return "User registered successfully";
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }
}*/

/*package com.example.reactauth.service;

import com.example.reactauth.model.User;
import com.example.reactauth.repository.UserRepository;
import com.example.reactauth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // Register method
    public String register(String username, String password, String role) {
        // ❌ Prevent anyone from registering as ADMIN
        if ("ADMIN".equalsIgnoreCase(role)) {
            throw new RuntimeException("You cannot register as ADMIN.");
        }

        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        userRepository.save(user);
        return "User registered successfully";
    }

    // Login method
    public String login(String username, String password) {
        // ✅ Hardcoded admin account
        if ("admin".equalsIgnoreCase(username) && "123".equals(password)) {
            return jwtUtil.generateToken("admin", "ADMIN");
        }

        // Otherwise check user in DB
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }
}*/

package com.example.reactauth.service;

import com.example.reactauth.model.User;
import com.example.reactauth.repository.UserRepository;
import com.example.reactauth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // Register method
    public String register(String username, String password, String role) {
        // ❌ Prevent anyone from registering as ADMIN or PI
        if ("ADMIN".equalsIgnoreCase(role) || "PI".equalsIgnoreCase(role)) {
            throw new RuntimeException("You cannot register as " + role + ".");
        }

        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        userRepository.save(user);
        return "User registered successfully";
    }

    // Login method
    public String login(String username, String password) {
        // ✅ Hardcoded admin account
        if ("admin".equalsIgnoreCase(username) && "123".equals(password)) {
            return jwtUtil.generateToken("admin", "ADMIN");
        }

        // ✅ Hardcoded PI account
        if ("Personal Instructor".equalsIgnoreCase(username) && "123".equals(password)) {
            return jwtUtil.generateToken("Personal Instructor", "PI");
        }

        // Otherwise check user in DB
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }
}


