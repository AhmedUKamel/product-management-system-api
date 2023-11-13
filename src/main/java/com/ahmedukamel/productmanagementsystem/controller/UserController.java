package com.ahmedukamel.productmanagementsystem.controller;

import com.ahmedukamel.productmanagementsystem.model.User;
import com.ahmedukamel.productmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ahmedukamel.productmanagementsystem.config.Constant.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> profile(@RequestHeader("Authentication") final String jwtToken) {
        return ResponseEntity.ok(userService.profile(jwtToken));
    }
}
