package com.isndila.wallet_api.controller;

import com.isndila.wallet_api.entity.DigitalCard;
import com.isndila.wallet_api.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cards")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService){
        this.cardService = cardService;
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<DigitalCard>> getAccountCard(@PathVariable UUID accountId){
        return ResponseEntity.ok(cardService.getCardsForAccount(accountId));
    }

    @PostMapping("/account/{accountId}/issue")
    public ResponseEntity<DigitalCard> issueCard(@PathVariable UUID accountId){
        return ResponseEntity.ok(cardService.provisionDigitalCard(accountId));
    }
}
