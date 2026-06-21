package com.isndila.wallet_api.repo;

import com.isndila.wallet_api.entity.Account;
import com.isndila.wallet_api.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    List<Transaction> findBySourceAccountOrDestinationAccountOrderByTimestampDesc(Account sourceAccount, Account destinationAccount);
}
