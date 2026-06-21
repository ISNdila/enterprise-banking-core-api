package com.isndila.wallet_api.service;

import com.isndila.wallet_api.dto.LoginRequest;
import com.isndila.wallet_api.dto.RegisterRequest;
import com.isndila.wallet_api.entity.User;
import com.isndila.wallet_api.entity.Wallet;
import com.isndila.wallet_api.repo.UserRepo;
import com.isndila.wallet_api.repo.WalletRepo;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AuthService {
    private final UserRepo userRepo;
    private final WalletRepo walletRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepo userRepo, WalletRepo walletRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.walletRepo = walletRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerUSer(RegisterRequest request){
        if (userRepo.existsByUsername(request.getUsername())){
            throw new IllegalArgumentException("Username is already taken. Try another one.");
        }
        if (userRepo.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("Email address is already registered. Try another one.");
        }

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        newUser.setEmail(request.getEmail());
        newUser.setRole("CUSTOMER");

        User savedUser = userRepo.save(newUser);

        Wallet initialWallet = new Wallet();
        initialWallet.setUser(savedUser);
        initialWallet.setBalance(BigDecimal.valueOf(100.00));
        initialWallet.setCurrency("ZAR");
        initialWallet.setCreatedAt(LocalDateTime.now());

        walletRepo.save(initialWallet);
        return savedUser;
    }

    public String authenticateUser(LoginRequest request){
        User user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(()-> new RuntimeException("Invalid username or password"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())){
            throw new RuntimeException("Invalid username or password");
        }
        String result = "Authentication successful for user: ";
        return result + user.getUsername();
    }
}
