package com.telran.auth.dao.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name="hash_codes_table")
public class UserHashEntity {
        @Id
        String email;
        String hash;
        @OneToOne(optional = true ,mappedBy = "hashCode")
        UserCredentialsEntity user;

        @Override
        public String toString() {
                return "hash='" + hash + '\'' +
                        '}';
        }
}
