package com.telran.statistics.dao;

import com.telran.statistics.dao.entity.TheoryEntity;

import java.util.List;

public interface StatisticsRepo {
TheoryEntity getTheoryEntity(String email, String appId);
}
