package com.telran.auth.service;

import com.telran.auth.dao.UserCredentialsRepo;
import com.telran.auth.dao.entity.UserCredentialsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserCredentialsServiceImpl implements UserCredentialsService {

    private final UserCredentialsRepo repo;

    @Autowired
    public UserCredentialsServiceImpl(UserCredentialsRepo repo){
        this.repo=repo;
    }

    @Override
    public void addUser(UserCredentialsEntity entity) {
        entity.setHashCode(UUID.randomUUID().toString());
        repo.save(entity);
    }

    @Override
    public UserCredentialsEntity getUser(String email) {
        return repo.findById(email).get();
    }

    @Override
    public UserCredentialsEntity forgetPassword(String email) {
        UserCredentialsEntity entity = repo.findById(email).get();
        entity.setHashCode(UUID.randomUUID().toString());
        repo.save(entity);
        return null;
    }

    @Override
    public void getHash(String hash, String email) {

    }

    @Override
    public String getHashByEmail(String email) {
        return repo.findById(email).get().getHashCode();
    }

    @Override
    public void putUser(UserCredentialsEntity entity, String hash) {
        UserCredentialsEntity entityFromRepo = repo.findById(entity.getEmail()).get();
        if (!entityFromRepo.getHashCode().equals(hash)){
            throw new RuntimeException("Link has problem");
        }
        entity.setHashCode(null);
        repo.save(entity);
    }


}
