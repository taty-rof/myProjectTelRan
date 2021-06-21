package com.telran.auth.dao;

import com.telran.auth.dao.entity.UserProfileEntity;

public interface UserPofileRepo {
    String addUser(UserProfileEntity user);
    UserProfileEntity getUser(String email);
    void updateUser(UserProfileEntity user);
    void deleteUser(String userEmail);
}
