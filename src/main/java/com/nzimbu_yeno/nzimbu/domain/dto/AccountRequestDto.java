package com.nzimbu_yeno.nzimbu.domain.dto;

import com.nzimbu_yeno.nzimbu.domain.AccountStatus;
import com.nzimbu_yeno.nzimbu.domain.AccountType;
import com.nzimbu_yeno.nzimbu.domain.entity.Transactions;
import com.nzimbu_yeno.nzimbu.domain.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountRequestDto {

    private UUID id;
    private String userId;
    @NotNull(message = "You need to specify the type of account you wish to create with us e.g, SAVING, CHEQUE, FIXED_DEPOSIT, BUSINESS")
    private AccountType accountType;
    private String accountNumber;
    private BigDecimal accountBalance;
    private List<Transactions> transactions = new ArrayList<>();
    private AccountStatus accountStatus;
    private LocalDateTime createdAt;
}
