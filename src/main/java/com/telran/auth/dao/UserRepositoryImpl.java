package com.telran.auth.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Primary
public class UserRepositoryImpl implements UserRepo {

    private final Map<String, UserEntity> allUsersMap = new ConcurrentHashMap<>();

    @Autowired
    PasswordEncoder passwordencoder;

    @Autowired
    public UserRepositoryImpl(PasswordEncoder passwordencoder){
        this.passwordencoder=passwordencoder;

        allUsersMap.put("admin@mail.com",
                        UserEntity.builder()
                        .username("admin@mail.com")
                                .password("{noop}admin")
                                .roles(new String[]{"ROLE_ADMIN","ROLE_USER"})
                        .build());
    }

    @Override
    public void addUser(String email, UserEntity user) {
        if(allUsersMap.putIfAbsent(email, user) != null){
//            throw new UnauthorizedError(String.format("User with username: %s already exists!",email));
            throw new RuntimeException(String.format("User with username: %s already exists!",email));
        }
    }


    @Override
    public UserEntity getUser(String email) {
        UserEntity user = allUsersMap.get(email);
        if(user == null){
            throw new RuntimeException(String.format("User with username: %s does not exist",email));
        }
        return user;
    }
}
