package com.nzimbu_yeno.nzimbu.domain.dto;

import com.nzimbu_yeno.nzimbu.domain.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteAccountRequest {

    private String userId;
    private AccountType accountType;
    private String accountNumber;
}
