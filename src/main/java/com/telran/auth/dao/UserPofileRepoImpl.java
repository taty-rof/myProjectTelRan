package com.telran.auth.dao;

import com.telran.auth.dao.entity.UserProfileEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.*;

@Repository
public class UserPofileRepoImpl implements UserPofileRepo {

    private final Map<String, UserProfileEntity> allUsersMap = new ConcurrentHashMap<>();
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();

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
        try{
            writeLock.lock();
            String id = UUID.randomUUID().toString();
        user.setId(id);
        if(allUsersMap.putIfAbsent(user.getEmail(), user) != null){
//            throw new UnauthorizedError(String.format("User with username: %s already exists!",user.getEmail()));
            throw new RuntimeException(String.format("User with username: %s already exists!",user.getEmail()));
        }
        return id;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public UserProfileEntity getUser(String email) {
        try{
           readLock.lock();
        UserProfileEntity user = allUsersMap.get(email);
        if(user == null){
            throw new RuntimeException(String.format("User with username: %s does not exist",email));
        }
        return user;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void updateUser(UserProfileEntity user) {
        try {
            writeLock.lock();
            UserProfileEntity removed = allUsersMap.remove(user.getEmail());
            if (removed == null) {
//            throw new ContactNotFoundException("Contact with id: %s not found".formatted(contact.getId()));
                throw new RuntimeException(String.format("Contact with id: %s not found", user.getEmail()));
            }
            if (!(removed.getEmail().equals(user.getEmail())) || !(removed.getId().equals(user.getId()))) {
                allUsersMap.put(removed.getEmail(), removed);
//            throw new ContactAlreadyExistException("Contact with email: %s already exists".formatted(user.getEmail()));
                throw new RuntimeException("Contact with email: %s already exists");
            }
            allUsersMap.put(user.getEmail(), user);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void deleteUser(String userEmail) {
        try {
            writeLock.lock();
            UserProfileEntity removed = allUsersMap.remove(userEmail);
            if (removed == null) {
                throw new RuntimeException(String.format("Contact with id: %s not found"));
            }
        } finally {
            writeLock.unlock();
        }

    }

}
