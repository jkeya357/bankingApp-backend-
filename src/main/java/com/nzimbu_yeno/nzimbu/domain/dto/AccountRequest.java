package com.nzimbu_yeno.nzimbu.domain.dto;

import com.nzimbu_yeno.nzimbu.domain.AccountStatus;
import com.nzimbu_yeno.nzimbu.domain.AccountType;
import com.nzimbu_yeno.nzimbu.domain.entity.Transactions;
import com.nzimbu_yeno.nzimbu.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountRequest {

    private UUID id;
    private String userId;
    private AccountType accountType;
    private String accountNumber;
    private BigDecimal accountBalance;
    private List<Transactions> transactions = new ArrayList<>();
    private AccountStatus accountStatus;
    private LocalDateTime createdAt;
}
