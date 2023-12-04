package com.example.demo.controller;


import com.example.demo.service.EmailService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final EmailService emailService;

    @GetMapping("/register")
    public ResponseEntity<?> register(Principal principal){
        if (principal instanceof AbstractAuthenticationToken){
            emailService.ff();
            return ResponseEntity.ok(userService.getUserAuthentication((AbstractAuthenticationToken) principal));
        }
        return ResponseEntity.ok("Error occurred");
    }

}
