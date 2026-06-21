package com.isndila.wallet_api.controller;

import com.isndila.wallet_api.dto.LoginRequest;
import com.isndila.wallet_api.dto.RegisterRequest;
import com.isndila.wallet_api.entity.User;
import com.isndila.wallet_api.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest request){
        User registeredUser = authService.registerUSer(request);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest request){
        String statusMessage = authService.authenticateUser(request);
        return ResponseEntity.ok(statusMessage);
    }
}
