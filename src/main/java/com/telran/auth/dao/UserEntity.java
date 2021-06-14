package com.telran.auth.dao;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserEntity {
    String username;
    String password;
    String[] roles;
    Boolean enabled;
}
