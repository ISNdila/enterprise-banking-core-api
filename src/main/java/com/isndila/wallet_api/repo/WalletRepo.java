package com.isndila.wallet_api.repo;

import com.isndila.wallet_api.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletRepo extends JpaRepository<Wallet, UUID> {
}
