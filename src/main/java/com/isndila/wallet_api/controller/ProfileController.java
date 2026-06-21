package com.isndila.wallet_api.controller;

import com.isndila.wallet_api.dto.UpdateProfileRequest;
import com.isndila.wallet_api.entity.User;
import com.isndila.wallet_api.repo.UserRepo;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final UserRepo userRepo;

    public ProfileController(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @GetMapping
    public ResponseEntity<User> viewProfile(@AuthenticationPrincipal User principalUser){
        return ResponseEntity.ok(principalUser);
    }

    @PostMapping("/language")
    public ResponseEntity<User> updateLanguage(@AuthenticationPrincipal User principalUser, @Valid @RequestBody UpdateProfileRequest request){
        principalUser.setPreferredLanguage(request.getPreferredLanguage());
        User updatedUser = userRepo.save(principalUser);
        return ResponseEntity.ok(updatedUser);
    }
}
