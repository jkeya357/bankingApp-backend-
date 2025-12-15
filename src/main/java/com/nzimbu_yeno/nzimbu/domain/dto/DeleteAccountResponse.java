package com.nzimbu_yeno.nzimbu.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteAccountResponse {

    private String accountNumber;
    private String message;
}
