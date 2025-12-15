package com.nzimbu_yeno.nzimbu.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuthResponse {

    private String token;
    private String refreshToken;
    private long expiresIn;
    private UUID userId;
}
