package com.isndila.wallet_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class StatementResponse {
    private String accountNumber;
    private String accountType;
    private BigDecimal currentBalance;
    private LocalDateTime generationTimestamp;
    private List<TransactionRecord> transactionHistory;

    @Data
    @AllArgsConstructor
    public static class TransactionRecord {
        private Long transactionId;
        private LocalDateTime timestamp;
        private String description;
        private BigDecimal amount;
        private String status;
    }
}
