package com.telran.auth.service;

import com.telran.auth.dao.UserCredentialsRepo;
import com.telran.auth.dao.UserPofileRepo;
import com.telran.auth.dao.entity.UserCredentialsEntity;
import com.telran.auth.dao.entity.UserProfileEntity;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    private final UserPofileRepo userRepo;
    private final UserCredentialsRepo credentialsRepo;
    @Value("${admin}")
    private String adminName;

    @Autowired
    public UserProfileServiceImpl(UserPofileRepo userRepo,UserCredentialsRepo credentialsRepo){
        this.credentialsRepo=credentialsRepo;
        this.userRepo = userRepo;
    }

    @Override
    public String addUser(UserProfileEntity entity) {
        System.out.println(entity);
        if (userRepo.existsById(entity.getEmail())){
            throw new RuntimeException("This student already exists");
        }
        userRepo.save(entity);
        UserCredentialsEntity userCredentialsEntity = credentialsRepo.findById(entity.getEmail()).get();
        userCredentialsEntity.setHashCode(null);
        credentialsRepo.save(userCredentialsEntity);
        return entity.getEmail();
    }

    @Override
    public UserProfileEntity getUser(String email, String requestEmail) {
        if (!checkingUser(email,requestEmail)){
            throw new RuntimeException("You can't update this user profile");
        }
        if (!userRepo.existsById(email)){
            throw new RuntimeException("This student doesn't exist");
        }
        return userRepo.findById(email).get();
    }

    @Override
    public void updateUser(UserProfileEntity entity, String email, String requestEmail) {
        if (!checkingUser(email,requestEmail)
                && !email.equals(entity.getEmail())){
            throw new RuntimeException("You can't update this user profile");
        }
        if (!userRepo.existsById(entity.getEmail())){
            throw new RuntimeException("This student doesn't exist");
        }
        userRepo.save(entity);
    }

    @Override
    public void deleteUser(String userEmail) {
        UserProfileEntity entity = userRepo.findById(userEmail).orElse(null);
        if (entity==null){
            throw new RuntimeException("This student doesn't exist");
        }
        userRepo.delete(entity);
    }


    //**********Checking if userCredential's email equals to pathVariable email, but ADMIN can get info*************
    private Boolean checkingUser(String userEmail, String requestEmail){
        if (!requestEmail.equals(userEmail)&&(!requestEmail.equals(adminName))){
                return false;
        }
        return true;
    }
}
