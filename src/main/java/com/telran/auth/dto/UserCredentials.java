package com.telran.auth.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserCredentials {
    @Email(message = "Email should be valid")
    String email;

    @NotNull(message = "Password cannot be null")
    String password;
}
