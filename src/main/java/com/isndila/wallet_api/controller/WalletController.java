package com.isndila.wallet_api.controller;

import com.isndila.wallet_api.dto.TransferRequest;
import com.isndila.wallet_api.entity.Transaction;
import com.isndila.wallet_api.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService){
        this.walletService = walletService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> handleTransfer(@Valid @RequestBody TransferRequest request){
        Transaction tx = walletService.transferFunds(request);
        return new ResponseEntity<>(tx, HttpStatus.CREATED);
    }
}
