package com.nzimbu_yeno.nzimbu.domain.dto;

import com.nzimbu_yeno.nzimbu.domain.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private UUID id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String profilePicture;
    private List<AccountDto> accounts = new ArrayList<>();
    private LocalDateTime createdAt;
}
