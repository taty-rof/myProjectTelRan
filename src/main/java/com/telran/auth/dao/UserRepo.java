package com.telran.auth.dao;

public interface UserRepo {
    void addUser(String email, UserEntity user);
    UserEntity getUser(String email);
}
