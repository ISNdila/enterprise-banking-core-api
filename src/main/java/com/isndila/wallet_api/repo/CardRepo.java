package com.isndila.wallet_api.repo;

import com.isndila.wallet_api.entity.Account;
import com.isndila.wallet_api.entity.DigitalCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepo extends JpaRepository<DigitalCard, Long> {
    List<DigitalCard> findByAccount(Account account);
    List<DigitalCard> findByAccountAndIsActiveTrue(Account account);
}
