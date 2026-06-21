package com.isndila.wallet_api.controller;

import com.isndila.wallet_api.entity.Account;
import com.isndila.wallet_api.entity.User;
import com.isndila.wallet_api.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<Account>> viewMyAccounts(@AuthenticationPrincipal User principalUser){
        return ResponseEntity.ok(accountService.getAccountsByUser(principalUser));
    }

    @PostMapping("/create")
    public ResponseEntity<Account> openAccount(@AuthenticationPrincipal User principalUser, @RequestParam String type){
        return ResponseEntity.ok(accountService.provisionNewAccount(principalUser, type));
    }
}
