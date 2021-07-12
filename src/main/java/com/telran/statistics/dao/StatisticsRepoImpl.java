package com.telran.statistics.dao;

import com.telran.statistics.dao.entity.TheoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


@Repository
public class StatisticsRepoImpl implements StatisticsRepo{
    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public TheoryEntity getTheoryEntity(String email, String appId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        TheoryEntity entity = mongoTemplate.findOne(query,TheoryEntity.class);
        return entity;
    }
}
