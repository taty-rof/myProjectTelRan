package com.telran.auth.dao;

import com.telran.auth.dao.entity.UserCredentialsEntity;

public interface UserCredentialsRepo {
    void addUser(UserCredentialsEntity user);
    UserCredentialsEntity getUser(String email);
    void addRoleStudent(String email);
    void getHash(String hash, String userEmail);

}
