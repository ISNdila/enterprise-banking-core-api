package com.isndila.wallet_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "wallets")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    @Id
    private UUID id = UUID.randomUUID();

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;
    @Column(nullable = false)
    private String currency = "ZAR";
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
