package com.telran.generalPage.dto;

import lombok.*;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class MessageFromUserDto {
    @Size(min = 1, max = 200, message
            = "About Me must be between 10 and 200 characters")
    String message;

    @Email(message = "Email should be valid")
    String email;

    @NotNull(message = "Name cannot be null")
    String fullName;
}
