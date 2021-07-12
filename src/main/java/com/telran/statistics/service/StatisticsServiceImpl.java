package com.telran.statistics.service;

import com.telran.statistics.dao.StatisticsRepo;
import com.telran.statistics.service.model.TheoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService{

    final private StatisticsRepo repo;

    @Autowired
    public StatisticsServiceImpl(StatisticsRepo repo){
        this.repo=repo;
    }

    @Override
    public String getTheoryStatistics(TheoryModel theoryModel) {
        System.out.println(repo.getTheoryEntity(theoryModel.getEmail(),theoryModel.getItemId()));
        return "0";
    }
}
