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
    String username;
    @Column(nullable=false)
    String password;
    String[] roles;
    Boolean enabled;
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "hashCode",nullable = true)
    UserHashEntity hashCode;
}
