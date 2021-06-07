package com.telran.generalPageTests.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserCredentials {
    String email;
    String password;
}
