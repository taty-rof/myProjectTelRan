package com.telran.generalPage.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(exclude = {"id"})
public class Application {
    @NotNull(message = "Id cannot be null")
    int id;

    @NotNull(message = "Name cannot be null")
    String name;

    @NotNull(message = "Category cannot be null")
    String category;

    @NotNull(message = "Title cannot be null")
    String title;

    @Size(min = 10, max = 200, message
            = "About Me must be between 10 and 200 characters")
    String description;
}
