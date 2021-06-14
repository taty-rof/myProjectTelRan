package com.telran.auth.service;

import com.telran.auth.dao.UserCredentialsRepo;
import com.telran.auth.dao.UserPofileRepo;
import com.telran.auth.dao.UserProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    UserPofileRepo userRepo;

    @Autowired
    public UserProfileServiceImpl(UserPofileRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public String addUser(UserProfileEntity entity) {
        return userRepo.addUser(entity);
    }

    @Override
    public UserProfileEntity getUser(String email) {
        return userRepo.getUser(email);
    }
}
