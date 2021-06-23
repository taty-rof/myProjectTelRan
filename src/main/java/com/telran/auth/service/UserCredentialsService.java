package com.telran.auth.service;

import com.telran.auth.dao.entity.UserCredentialsEntity;

public interface UserCredentialsService {
    void addUser(UserCredentialsEntity entity);
    UserCredentialsEntity getUser(String email);
    void getHash(String hash, String email);
    String getHashByEmail(String email);
}
