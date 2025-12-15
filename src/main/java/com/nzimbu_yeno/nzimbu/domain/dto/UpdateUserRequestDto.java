package com.nzimbu_yeno.nzimbu.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequestDto {

    @Size(min = 3, message = "username must contain at least 3 characters")
    private String username;
    @Size(min = 3, message = "first name must contain at least 3 characters")
    private String firstName;
    @Size(min = 3, message = "last name must contain at least 3 characters")
    private String lastName;
    @Email(message = "please enter a valid email")
    private String email;
    @Size(min = 8, max = 16, message = "password must be between {min} and {max} characters")
    private String password;
    private String profilePicture;
}
