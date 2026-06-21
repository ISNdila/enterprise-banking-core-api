package com.isndila.wallet_api.service;

import com.isndila.wallet_api.dto.TransferRequest;
import com.isndila.wallet_api.entity.Transaction;
import com.isndila.wallet_api.entity.Wallet;
import com.isndila.wallet_api.repo.TransactionRepo;
import com.isndila.wallet_api.repo.WalletRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WalletService {
    private final WalletRepo walletRepo;
    private final TransactionRepo transactionRepo;

    public WalletService(WalletRepo walletRepo, TransactionRepo transactionRepo){
        this.walletRepo = walletRepo;
        this.transactionRepo = transactionRepo;
    }

    @Transactional
    public Transaction transferFunds(TransferRequest request){
        if (request.getSourceWalletId().equals(request.getDestinationWalletId())){
            throw new IllegalArgumentException("Cannot transfer money to the same wallet.");
        }

        Wallet source = walletRepo.findById(request.getSourceWalletId())
                .orElseThrow(() -> new RuntimeException("Source wallet not found."));
        Wallet destination = walletRepo.findById(request.getDestinationWalletId())
                .orElseThrow(() -> new RuntimeException("Destination wallet not found."));
        if (source.getBalance().compareTo(request.getAmount()) < 0){
            throw new IllegalArgumentException("Insufficient funds in source wallet.");
        }

        source.setBalance(source.getBalance().subtract(request.getAmount()));
        destination.setBalance(destination.getBalance().add(request.getAmount()));

        walletRepo.save(source);
        walletRepo.save(destination);

        Transaction tx = new Transaction();
        tx.setSourceWallet(source);
        tx.setDestinationWallet(destination);
        tx.setAmount(request.getAmount());
        tx.setTimestamp(LocalDateTime.now());
        tx.setStatus("SUCCESS");

        return transactionRepo.save(tx);
    }
}
