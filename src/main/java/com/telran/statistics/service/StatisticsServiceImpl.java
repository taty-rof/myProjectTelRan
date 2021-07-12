package com.telran.statistics.service;

import com.telran.statistics.dao.StatisticsRepo;
import com.telran.statistics.dao.entity.TheoryEntity;
import com.telran.statistics.dao.entity.TheoryItem;
import com.telran.statistics.service.model.TheoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class StatisticsServiceImpl implements StatisticsService{

    final private StatisticsRepo repo;
    @Value("${admin}")
    private String adminName;

    @Autowired
    public StatisticsServiceImpl(StatisticsRepo repo){
        this.repo=repo;
    }

    @Override
    public String getTheoryStatistics(TheoryModel theoryModel) {
         if (!checkingUser(theoryModel.getEmail(),theoryModel.getRequestEmail())){
            throw new RuntimeException("You can't get this user profile");
        }
        TheoryEntity entity = repo.getTheoryEntity(theoryModel.getEmail(),theoryModel.getItemId());
        if (entity.getItems()==null){
            throw new RuntimeException("There is no information about this section");
        }
        long count = Arrays.stream(entity.getItems()).count();
        int res = Arrays.stream(entity.getItems()).map(TheoryItem::getProgress).reduce(0, Integer::sum);
        return String.valueOf(res/count);
    }

    private Boolean checkingUser(String userEmail, String requestEmail){
        if (!requestEmail.equals(userEmail)){
            if (!requestEmail.equals(adminName)) {
                return false;
            }
        }
        return true;
    }
}
