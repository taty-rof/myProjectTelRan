package com.telran.auth.dao;

public interface UserCredentialsRepo {
    void addUser(String email, UserCredentialsEntity user);
    UserCredentialsEntity getUser(String email);
    void addRoleStudent(String email);
}
