package com.isndila.wallet_api.controller;

import com.isndila.wallet_api.dto.StatementResponse;
import com.isndila.wallet_api.entity.Transaction;
import com.isndila.wallet_api.service.StatementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/statements")
public class StatementController {
    private final StatementService statementService;

    public StatementController(StatementService statementService){
        this.statementService = statementService;
    }

    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<Transaction>> viewTransactionHistory(@PathVariable UUID accountId){
        return ResponseEntity.ok(statementService.getTransactionHistory(accountId));
    }

    @GetMapping("/{accountId}/download")
    public ResponseEntity<StatementResponse> downloadStatement(@PathVariable UUID accountId){
        return ResponseEntity.ok(statementService.compileBankStatement(accountId));
    }
}
