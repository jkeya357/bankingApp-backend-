package com.nzimbu_yeno.nzimbu.domain.dto;

import com.nzimbu_yeno.nzimbu.domain.TransactionType;
import com.nzimbu_yeno.nzimbu.domain.entity.Account;
import jakarta.validation.constraints.NotNull;
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
public class TransactionRequestDto {

    private UUID id;
    @NotNull(message = "please enter the account number")
    private String accountNumber;
    @NotNull(message = "please specify the type of transaction you'd like to perform")
    private TransactionType transactionType;
    private String description;
    @NotNull(message = "The amount cannot be empty and must be a multiple of 10")
    private BigDecimal amount;
    private LocalDateTime transactionDate;
}
