package com.telran.auth.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserProfile {
    String id;
    String firstName;
    String lastName;
    String email;
    String password;
    String institute;
    String degree;
    String fields;
    int[] apps;
    Boolean stillStudent;
}
