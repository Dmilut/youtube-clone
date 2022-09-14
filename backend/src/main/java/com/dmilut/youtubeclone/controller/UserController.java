package com.dmilut.youtubeclone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
/*
    private final UserRegistrationService userRegistrationService;

    @GetMapping("/register")
    public String register(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();

        userRegistrationService.registerUser(jwt.getTokenValue());

        return "User Registration successful";
    }*/
}
