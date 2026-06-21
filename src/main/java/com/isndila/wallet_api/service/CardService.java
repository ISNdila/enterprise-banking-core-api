package com.isndila.wallet_api.service;

import com.isndila.wallet_api.entity.Account;
import com.isndila.wallet_api.entity.DigitalCard;
import com.isndila.wallet_api.repo.AccountRepo;
import com.isndila.wallet_api.repo.CardRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class CardService {
    private final CardRepo cardRepo;
    private final AccountRepo accountRepo;

    public CardService(CardRepo cardRepo, AccountRepo accountRepo){
        this.cardRepo = cardRepo;
        this.accountRepo = accountRepo;
    }

    public List<DigitalCard> getCardsForAccount(UUID accountId){
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account unresolved"));
        return cardRepo.findByAccount(account);
    }

    public DigitalCard provisionDigitalCard(UUID accountId){
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account unresolved"));
        DigitalCard card = new DigitalCard();
        card.setAccount(account);
        card.setCardholderName(account.getUser().getUsername().toUpperCase());
        card.setCardNumber(String.format("5412-%04d-%04d-%04d", (int)(Math.random() * 10000),
                (int)(Math.random()*10000), (int)(Math.random()*10000), (int)(Math.random()*10000)

                ));
        card.setExpiryDate(LocalDate.now().plusYears(4));
        card.setCvv(String.format("%03d", (int)(Math.random()*1000)));
        card.setActive(true);

        return cardRepo.save(card);
    }
}
