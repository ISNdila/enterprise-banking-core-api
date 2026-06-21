package com.isndila.wallet_api.repo;

import com.isndila.wallet_api.entity.Account;
import com.isndila.wallet_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepo extends JpaRepository<Account, UUID> {
    List<Account> findByUser(User user);
    Optional<Account> findByAccountNumber(String accountNumber);
    boolean existsByAccountNumber(String accountNumber);
}
