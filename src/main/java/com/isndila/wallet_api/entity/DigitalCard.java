package com.isndila.wallet_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "digital_cards")
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
public class DigitalCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(nullable = false)
    private String cardNumber; // Tokenised 16-digig card number pan string representation
    @Column(nullable = false)
    private String cardholderName;
    @Column(nullable = false)
    private LocalDate expiryDate;
    @Column(nullable = false)
    private String cvv; // Dynamic security CVV string
    @Column(nullable = false)
    private boolean isActive = true;
}
