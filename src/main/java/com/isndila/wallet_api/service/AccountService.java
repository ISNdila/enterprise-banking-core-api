package com.isndila.wallet_api.service;

import com.isndila.wallet_api.entity.Account;
import com.isndila.wallet_api.entity.Transaction;
import com.isndila.wallet_api.entity.User;
import com.isndila.wallet_api.repo.AccountRepo;
import com.isndila.wallet_api.repo.TransactionRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {
    private final AccountRepo accountRepo;
    private final TransactionRepo transactionRepo;

    public AccountService(AccountRepo accountRepo, TransactionRepo transactionRepo){
        this.accountRepo = accountRepo;
        this.transactionRepo = transactionRepo;
    }
    public List<Account> getAccountsByUser(User user){
        return accountRepo.findByUser(user);
    }

    @Transactional
    public Account provisionNewAccount(User user, String accountType){
        Account account = new Account();
        account.setUser(user);
        account.setAccountType(accountType.toUpperCase());
        account.setAccountNumber(String.format("%010d", (long) (Math.random() * 10000000000L)));
        account.setBalance(accountType.equalsIgnoreCase("CREDIT") ? BigDecimal.valueOf(5000.00) : BigDecimal.ZERO);
        return accountRepo.save(account);
    }

    @Transactional
    public Transaction transferFunds(UUID srcId, UUID destId, BigDecimal amount, String narrative){
        if (srcId.equals(destId)) throw new IllegalArgumentException("Cannot send to the same account");

        Account src = accountRepo.findById(srcId).orElseThrow(() -> new RuntimeException("Source account missing"));
        Account dest = accountRepo.findById(destId).orElseThrow(() -> new RuntimeException("Destination account missing"));

        if (src.getBalance().compareTo(amount) < 0) throw new IllegalArgumentException("Insufficient funds");
        src.setBalance(src.getBalance().subtract(amount));
        dest.setBalance(dest.getBalance().add(amount));

        accountRepo.save(src);
        accountRepo.save(dest);

        Transaction tx = new Transaction();
        tx.setSourceWallet(tx.getSourceWallet());
        tx.setDestinationWallet(tx.getDestinationWallet());
        tx.setDescription(tx.getDescription());
        tx.setTimestamp(tx.getTimestamp());
        tx.setStatus("SUCCESS");

        return transactionRepo.save(tx);
    }
}
