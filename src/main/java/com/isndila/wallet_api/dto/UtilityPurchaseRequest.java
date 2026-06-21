package com.isndila.wallet_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class UtilityPurchaseRequest {
    @NotNull(message = "Wallet ID is required")
    private UUID walletId;
    @NotNull(message = "Product ID is required")
    private Long productId;
}
