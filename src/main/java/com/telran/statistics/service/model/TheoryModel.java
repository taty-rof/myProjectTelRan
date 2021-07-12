package com.telran.statistics.service.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
//@EqualsAndHashCode(of="id")
public class TheoryModel {
    String email;
    String itemId;
    String requestEmail;
}
