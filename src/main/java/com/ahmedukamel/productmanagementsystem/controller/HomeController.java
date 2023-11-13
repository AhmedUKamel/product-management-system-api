package com.ahmedukamel.productmanagementsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public ResponseEntity<String> homePage() {
        return ResponseEntity.ok("Welcome in Product Management System API, /api/v1/** is available now");
    }
}
