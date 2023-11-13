package com.ahmedukamel.productmanagementsystem.service;

import com.ahmedukamel.productmanagementsystem.model.User;
import com.ahmedukamel.productmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public User profile(final String jwtToken) {
        final String email = jwtTokenProvider.getEmailFromJwtToken(jwtToken).toLowerCase().strip();
        if (email != null) {
            User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Email not found"));
            if (jwtTokenProvider.getPasswordFromJwtToken(jwtToken).equals(user.getPassword())) {
                return user;
            } else {
                throw new RuntimeException("Invalid password");
            }
        }
        throw new RuntimeException("Invalid token");
    }
}
