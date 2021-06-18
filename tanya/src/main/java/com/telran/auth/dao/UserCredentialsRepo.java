package com.telran.auth.dao;

import com.telran.auth.dao.entity.UserCredentialsEntity;

public interface UserCredentialsRepo {
    void addUser(String email, UserCredentialsEntity user);
    UserCredentialsEntity getUser(String email);
    void addRoleStudent(String email);
}
