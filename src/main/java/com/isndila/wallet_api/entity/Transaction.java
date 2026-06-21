package com.isndila.wallet_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_wallet_id", nullable = false)
    private Wallet sourceWallet;
    @ManyToOne
    @JoinColumn(name = "destination_wallet_id", nullable = false)
    private Wallet destinationWallet;


    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();
    @Column(nullable = false)
    private String status;
}
