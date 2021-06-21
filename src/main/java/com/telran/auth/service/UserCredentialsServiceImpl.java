package com.telran.auth.service;

import com.telran.auth.dao.UserCredentialsRepo;
import com.telran.auth.dao.entity.UserCredentialsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialsServiceImpl implements UserCredentialsService {

    UserCredentialsRepo repo;

    @Autowired
    public UserCredentialsServiceImpl(UserCredentialsRepo repo){
        this.repo=repo;
    }

    @Override
    public void addUser(UserCredentialsEntity entity) {
        repo.addUser(entity);
    }

    @Override
    public UserCredentialsEntity getUser(String email) {
        return repo.getUser(email);
    }
}
