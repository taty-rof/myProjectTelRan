package com.telran.auth.controller.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserCredentialsDto {
    @NotBlank
    @Email(message = "Email should be valid")
    String email;

    @NotBlank
    @NotNull(message = "Password cannot be null")
    String password;
}
