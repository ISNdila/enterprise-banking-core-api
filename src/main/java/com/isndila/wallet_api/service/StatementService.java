package com.isndila.wallet_api.service;

import com.isndila.wallet_api.dto.StatementResponse;
import com.isndila.wallet_api.entity.Account;
import com.isndila.wallet_api.entity.Transaction;
import com.isndila.wallet_api.repo.AccountRepo;
import com.isndila.wallet_api.repo.TransactionRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StatementService {
    private final AccountRepo accountRepo;
    private final TransactionRepo transactionRepo;

    public StatementService(AccountRepo accountRepo, TransactionRepo transactionRepo){
        this.accountRepo = accountRepo;
        this.transactionRepo = transactionRepo;
    }

    public List<Transaction> getTransactionHistory(UUID accountId){
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account ledger metadata matching target unresolved"));
        return transactionRepo.findBySourceAccountOrDestinationAccountOrderByTimestampDesc(account, account);
    }
    public StatementResponse compileBankStatement(UUID accountId){
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Target statement reference criteria missing"));
        List<Transaction> records = transactionRepo.findBySourceAccountOrDestinationAccountOrderByTimestampDesc(account, account);

        List<StatementResponse.TransactionRecord> mappedHistory = records.stream().map(
                t -> {
                    BigDecimal signedAmount = t.getSourceWallet() != null && t.getSourceWallet().getId().equals(accountId)
                            ? t.getAmount().negate() : t.getAmount();
                    return new StatementResponse.TransactionRecord(
                            t.getId(), t.getTimestamp(), t.getDescription(), signedAmount, t.getStatus()
                    );
                }
        ).collect(Collectors.toList());

        return new StatementResponse(
                account.getAccountNumber(), account.getAccountType(), account.getBalance(), LocalDateTime.now(), mappedHistory
        );
    }
}
