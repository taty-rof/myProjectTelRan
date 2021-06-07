package com.telran.generalPage.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class MessageFromUser {
    String message;
    String email;
    String fullName;
}
