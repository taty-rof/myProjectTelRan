package com.telran.auth.dao;

import com.telran.auth.dao.entity.UserCredentialsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Repository
@Primary
public class UserCredentialsRepositoryImpl implements UserCredentialsRepo {

    private final Map<String, UserCredentialsEntity> allUsersMap = new ConcurrentHashMap<>();
    private final Map<String, String> hashMap = new ConcurrentHashMap<>();

    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();

    @Autowired
    PasswordEncoder passwordencoder;

    @Autowired
    public UserCredentialsRepositoryImpl(PasswordEncoder passwordencoder){
        this.passwordencoder=passwordencoder;
        UserCredentialsEntity entity =  UserCredentialsEntity.builder()
                .username("admin@mail.com")
                .password(passwordencoder.encode("12345"))
                .roles(new String[]{"ROLE_ADMIN","ROLE_USER","ROLE_STUDENT"})
                .build();
        allUsersMap.put("admin@mail.com",entity);
        //GENERATING HASH
        hashMap.put("admin@mail.com",Integer.toString(entity.hashCode()));
    }

    @Override
    public void addUser(UserCredentialsEntity user) {
        try {
            writeLock.lock();
            if (allUsersMap.putIfAbsent(user.getUsername(), user) != null) {
//            throw new UnauthorizedError(String.format("User with username: %s already exists!",email));
                throw new RuntimeException(String.format("User with username: %s already exists!", user.getUsername()));
            }
            //GENERATING HASH
            hashMap.putIfAbsent(user.getUsername(), Integer.toString(user.hashCode()));
            System.out.println("\n"+Integer.toString(user.hashCode())+"\n");
        }finally{
            writeLock.unlock();
        }
    }


    @Override
    public UserCredentialsEntity getUser(String email) {
        try {
            readLock.lock();
            UserCredentialsEntity user = allUsersMap.get(email);
            if (user == null) {
                throw new RuntimeException(String.format("User with username: %s does not exist", email));
            }
            return user;
        }finally {
            {
                readLock.unlock();
            }
        }
    }

    @Override
    public void addRoleStudent(String email) {
        try {
            writeLock.lock();
        UserCredentialsEntity entity = allUsersMap.get(email);
        if(entity == null){
            throw new RuntimeException(String.format("User with username: %s does not exist",email));
        }
        entity.setRoles(new String[]{"ROLE_USER","ROLE_STUDENT"});
        if(allUsersMap.put(email,entity) == null){
//            throw new UnauthorizedError(String.format("User with username: %s already exists!",email));
            throw new RuntimeException(String.format("User with username: %s already exists!",email));
        }
        hashMap.remove(email);
        }finally{
            writeLock.unlock();
        }
    }

    @Override
    public void checkHash(String hash, String email) {
        try {
            readLock.lock();
            if (!(hashMap.get(email).equals(hash))) {
                throw new RuntimeException(String.format("User with username: %s doesn't exist!", email));
            }
        }finally{
            readLock.unlock();
        }
    }

    @Override
    public String getHashByEmail(String userEmail) {
        try {
            readLock.lock();
            return hashMap.get(userEmail);
        }finally{
            readLock.unlock();
        }
    }
}
