package com.telran.auth.dao;

import com.telran.statistics.dto.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserPofileRepoImpl implements UserPofileRepo {

    private final Map<String, UserProfileEntity> allUsersMap = new ConcurrentHashMap<>();

    public UserPofileRepoImpl(){
        allUsersMap.put("admin@mail.com",
                UserProfileEntity.builder()
                        .id("12345")
                        .firstName("Admin")
                        .lastName("Adminovich")
                        .email("admin@mail.com")
                        .institute("Tel Aviv University")
                        .degree("B.Sc")
                        .fields("Computer Science")
                        .apps(new int[]{123})
                        .stillStudent(Boolean.TRUE)
                        .build());
    }

    @Override
    public String addUser(UserProfileEntity user) {
        String id = UUID.randomUUID().toString();
        user.setId(id);
        if(allUsersMap.putIfAbsent(user.getEmail(), user) != null){
//            throw new UnauthorizedError(String.format("User with username: %s already exists!",user.getEmail()));
            throw new RuntimeException(String.format("User with username: %s already exists!",user.getEmail()));
        }
        return id;
    }

    @Override
    public UserProfileEntity getUser(String email) {
        UserProfileEntity user = allUsersMap.get(email);
        if(user == null){
            throw new RuntimeException(String.format("User with username: %s does not exist",email));
        }
        return user;
    }
}
