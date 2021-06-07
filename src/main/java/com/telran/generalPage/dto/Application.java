package com.telran.generalPage.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(exclude = {"id"})
public class Application {
    int id;
    String name;
    String category;
    String title;
    String description;
}
