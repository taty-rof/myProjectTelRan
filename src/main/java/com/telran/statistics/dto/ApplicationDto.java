package com.telran.statistics.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApplicationDto {
    int id;
    String name;
    String category;
    String title;
    String description;
}
