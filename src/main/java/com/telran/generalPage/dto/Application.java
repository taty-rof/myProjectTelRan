package com.telran.generalPage.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Application {
    int id;
    String name;
    String category;
    String title;
    String description;
}
