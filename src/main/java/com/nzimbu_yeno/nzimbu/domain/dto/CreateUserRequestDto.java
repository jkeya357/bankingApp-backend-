package com.nzimbu_yeno.nzimbu.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserRequestDto {

    private UUID id;

    @NotNull(message = "first name cannot be empty")
    private String firstName;
    @NotNull(message = "last name cannot be empty")
    private String lastName;
    @NotNull(message = "email cannot be empty")
    private String email;
    @NotNull(message = "password is required")
    @Size(min = 8, max = 16, message = "password must be between {min} and {max} characters long")
    private String password;
    @NotNull(message = "a username is required")
    @Size(min = 3, message = "username must contain at least {min} characters")
    private String userName;
}
