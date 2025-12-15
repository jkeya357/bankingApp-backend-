package com.nzimbu_yeno.nzimbu.domain.dto;

import com.nzimbu_yeno.nzimbu.domain.TransactionType;
import com.nzimbu_yeno.nzimbu.domain.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionsRequest {

    private UUID id;
    private String accountNumber;
    private TransactionType transactionType;
    private String description;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
}
