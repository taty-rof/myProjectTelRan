package com.telran.generalPage.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class MessageFromUserDto {
    String message;
    String email;
    String fullName;
}
