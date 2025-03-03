package com.techeasy.library.service;

import com.techeasy.library.Repository.UserRepository;
import com.techeasy.library.entity.User;
import com.techeasy.library.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            System.out.println("User not found: " + username);
            throw new RuntimeException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            System.out.println("Password mismatch for user: " + username);
            throw new RuntimeException("Invalid username or password");
        }

        String token = jwtUtil.generateToken(username);
        System.out.println("Generated Token: " + token); // Debugging
        return token;
    }
    

    public void updatePassword() {
        String plainPassword = "Vidya@123";
        String hashedPassword = passwordEncoder.encode(plainPassword);
        
        System.out.println("🔑 Hashed Password: " + hashedPassword);
    }
}
