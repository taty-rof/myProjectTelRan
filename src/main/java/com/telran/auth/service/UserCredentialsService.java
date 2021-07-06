package com.telran.auth.service;

import com.telran.auth.dao.entity.UserCredentialsEntity;

public interface UserCredentialsService {
    void addUser(UserCredentialsEntity entity);
    UserCredentialsEntity getUser(String email);
    UserCredentialsEntity forgetPassword(String email);
    void getHashByEmail(String hash, String email);
    String getHashByEmail(String email);
    void putUser(String email, UserCredentialsEntity entity, String hash);

}
