package com.isndila.wallet_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "utility_products")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UtilityProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type; // "Airtime", "Electricity", "Voucher"
    @Column(nullable = false)
    private String provider; // "Vodacom", "Eskom", "Uber"
    @Column(nullable = false)
    private String name; // e.g., "R50 Airtime", "Prepaid Token", "R100 Gift Card"
    @Column(nullable = false)
    private BigDecimal price;
}
