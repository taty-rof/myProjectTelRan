package com.telran.auth.dao;

import com.telran.auth.dao.entity.UserCredentialsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Primary
public class UserCredentialsRepositoryImpl implements UserCredentialsRepo {

    private final Map<String, UserCredentialsEntity> allUsersMap = new ConcurrentHashMap<>();

    @Autowired
    PasswordEncoder passwordencoder;

    @Autowired
    public UserCredentialsRepositoryImpl(PasswordEncoder passwordencoder){
        this.passwordencoder=passwordencoder;

        allUsersMap.put("admin@mail.com",
                        UserCredentialsEntity.builder()
                        .username("admin@mail.com")
                                .password(passwordencoder.encode("12345"))
                                .roles(new String[]{"ROLE_ADMIN","ROLE_USER","ROLE_STUDENT"})
                        .build());
    }

    @Override
    public void addUser(String email, UserCredentialsEntity user) {
        if(allUsersMap.putIfAbsent(email, user) != null){
//            throw new UnauthorizedError(String.format("User with username: %s already exists!",email));
            throw new RuntimeException(String.format("User with username: %s already exists!",email));
        }
    }


    @Override
    public UserCredentialsEntity getUser(String email) {
        UserCredentialsEntity user = allUsersMap.get(email);
        System.out.println("============ "+user);
        if(user == null){
            throw new RuntimeException(String.format("User with username: %s does not exist",email));
        }
        return user;
    }

    @Override
    public void addRoleStudent(String email) {
        UserCredentialsEntity entity = allUsersMap.get(email);
        if(entity == null){
            throw new RuntimeException(String.format("User with username: %s does not exist",email));
        }
        entity.setRoles(new String[]{"ROLE_USER","ROLE_STUDENT"});
        if(allUsersMap.put(email,entity) == null){
//            throw new UnauthorizedError(String.format("User with username: %s already exists!",email));
            throw new RuntimeException(String.format("User with username: %s already exists!",email));
        }
    }
}
