package com.telran.auth.service;

import com.telran.auth.dao.UserPofileRepo;
import com.telran.auth.dao.entity.UserProfileEntity;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserPofileRepo userRepo;
    @Value("${admin}")
    private String adminName;

    @Autowired
    public UserProfileServiceImpl(UserPofileRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public String addUser(UserProfileEntity entity) {
        return userRepo.addUser(entity);
    }

    @Override
    public UserProfileEntity getUser(String email, String requestEmail) {
        if (!checkingUser(email,requestEmail)){
            throw new RuntimeException("You can't update this user profile");
        }
        return userRepo.getUser(email);
    }

    @Override
    public void updateUser(UserProfileEntity entity, String email, String requestEmail) {
        if (!checkingUser(email,requestEmail)
                && !email.equals(entity.getEmail())){
            throw new RuntimeException("You can't update this user profile");
        }
        userRepo.updateUser(entity);
    }

    @Override
    public void deleteUser(String userEmail) {
        userRepo.deleteUser(userEmail);
    }


    //**********Checking if userCredential's email equals to pathVariable email, but ADMIN can get info*************
    private Boolean checkingUser(String userEmail, String requestEmail){
        if (!requestEmail.equals(userEmail)&&(!requestEmail.equals(adminName))){
                return false;
        }
        return true;
    }
}
