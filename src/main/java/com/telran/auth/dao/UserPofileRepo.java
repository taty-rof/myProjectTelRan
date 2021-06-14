package com.telran.auth.dao;

public interface UserPofileRepo {
    String addUser(UserProfileEntity user);
    UserProfileEntity getUser(String email);
}
