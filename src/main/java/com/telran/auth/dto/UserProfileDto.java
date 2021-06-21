package com.telran.auth.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserProfileDto {
    @NotNull(message = "Id cannot be null")
    String id;

    @NotNull(message = "First name cannot be null")
    String firstName;

    @NotNull(message = "Last name cannot be null")
    String lastName;

    @Email(message = "Email should be valid")
    String email;

//    @NotNull(message = "Password cannot be null")
//    String password;

    @NotNull(message = "Institute cannot be null")
    String institute;

    @NotNull(message = "Degree cannot be null")
    String degree;

    @NotNull(message = "Fields cannot be null")
    String fields;

    @NotNull(message = "Apps cannot be null")
    int[] apps;

    Boolean stillStudent;
}
