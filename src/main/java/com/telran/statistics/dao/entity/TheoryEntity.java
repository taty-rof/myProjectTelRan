package com.telran.statistics.dao.entity;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(of="id")
@Document(collection = "theory")
public class TheoryEntity {
    @Id
    ObjectId id;
    String email;
    String appId;
    TheoryItem items[];
}
