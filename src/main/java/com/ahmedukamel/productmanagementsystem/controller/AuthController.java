package com.ahmedukamel.productmanagementsystem.controller;

import com.ahmedukamel.productmanagementsystem.dto.SignupRequest;
import com.ahmedukamel.productmanagementsystem.dto.SignupResponse;
import com.ahmedukamel.productmanagementsystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ahmedukamel.productmanagementsystem.config.Constant.BASE_URL;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_URL + "/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> register(@RequestBody SignupRequest request) {
        SignupResponse response = authService.register(request);
        if (response.isAccepted()) {
            return new ResponseEntity<>(response, CREATED);
        } else {
            return new ResponseEntity<>(response, CONFLICT);
        }
    }
}
