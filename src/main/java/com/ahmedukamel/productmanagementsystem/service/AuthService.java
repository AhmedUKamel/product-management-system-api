package com.ahmedukamel.productmanagementsystem.service;

import com.ahmedukamel.productmanagementsystem.dto.SignupRequest;
import com.ahmedukamel.productmanagementsystem.dto.SignupResponse;
import com.ahmedukamel.productmanagementsystem.model.User;
import com.ahmedukamel.productmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public SignupResponse register(SignupRequest request) {
        final String email = request.getEmail().toLowerCase().strip();
        if (userRepository.existsByEmail(email)) {
            return new SignupResponse(null, "Email already exist", false);
        }
        final String password = passwordEncoder.encode(request.getPassword());
        final String name = request.getName().strip();
        userRepository.save(User.builder().id(UUID.randomUUID().toString()).name(name).email(email).password(password).build());
        final String jwtToken = jwtTokenProvider.generateJwtToken(authenticate(email, password));
        return new SignupResponse(jwtToken, "Registered Successfully", true);
    }

    public Authentication authenticate(String email, String password) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }
}
