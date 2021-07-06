package com.telran.auth.dao.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(of = "email")
@Entity
@Table(name="students_table")
public class UserProfileEntity {

    @NotNull(message = "First name cannot be null")
    String firstName;

    @NotNull(message = "Last name cannot be null")
    String lastName;

    @Id
    @Email(message = "Email should be valid")
    String email;

    @NotNull(message = "Institute cannot be null")
    String institute;

    @NotNull(message = "Degree cannot be null")
    String degree;

    @NotNull(message = "Fields cannot be null")
    String fields;

    @NotNull(message = "Apps cannot be null")
    int[] apps;

    Boolean stillStudent;
}
