package com.telran.auth.service;

import com.telran.auth.dao.UserProfileEntity;

public interface UserProfileService {
    String addUser(UserProfileEntity entity);
    UserProfileEntity getUser(String email);
}
