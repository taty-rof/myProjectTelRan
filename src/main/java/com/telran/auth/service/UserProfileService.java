package com.telran.auth.service;

import com.telran.auth.dao.entity.UserProfileEntity;

public interface UserProfileService {
    String addUser(UserProfileEntity entity);
    UserProfileEntity getUser(String email, String requestEmail);
    void updateUser(UserProfileEntity entity, String email,String requestEmail);
    void deleteUser(String userEmail);

}
