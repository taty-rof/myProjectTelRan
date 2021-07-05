package com.telran.auth.dao.entity;
import lombok.*;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name="users_table")
public class UserCredentialsEntity {
    @Id
    @Column(unique=true,nullable=false)
    String email;
    @Column(nullable=false)
    String password;
    String[] roles;
    Boolean enabled;
    String hashCode;
}
