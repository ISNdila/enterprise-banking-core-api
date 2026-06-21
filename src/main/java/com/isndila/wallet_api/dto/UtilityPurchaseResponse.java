package com.isndila.wallet_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class UtilityPurchaseResponse {
    private String transactionStatus;
    private String productName;
    private BigDecimal amountDeducted;
    private String generatedTokenOrVoucher;
}
