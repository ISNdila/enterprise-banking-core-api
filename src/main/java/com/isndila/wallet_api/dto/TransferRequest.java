package com.isndila.wallet_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class TransferRequest {
    @NotNull(message = "Source wallet ID is required.")
    private UUID sourceWalletId;
    @NotNull(message = "Destination wallet ID is required")
    private UUID destinationWalletId;
    @NotNull(message = "Amount to be transfered is required.")
    private BigDecimal amount;
}
