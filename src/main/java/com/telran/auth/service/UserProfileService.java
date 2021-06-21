package com.telran.auth.service;

import com.telran.auth.dao.entity.UserProfileEntity;

public interface UserProfileService {
    String addUser(UserProfileEntity entity);
    UserProfileEntity getUser(String email);
    void updateUser(UserProfileEntity entity);
    void deleteUser(String userEmail);

}
