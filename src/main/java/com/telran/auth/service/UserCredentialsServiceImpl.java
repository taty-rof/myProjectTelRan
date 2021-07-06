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
        entity.setHashCode("registration_"+UUID.randomUUID().toString());
        repo.save(entity);
    }

    @Override
    public UserCredentialsEntity getUser(String email) {
        return repo.findById(email).get();
    }

    @Override
    public UserCredentialsEntity forgetPassword(String email) {
        UserCredentialsEntity entity = repo.findById(email).get();
        if (entity.getEnabled()){
            entity.setHashCode(UUID.randomUUID().toString());
            repo.save(entity);
            return entity;
        }
        throw new RuntimeException("Please complete your registration");
    }

    @Override
    public void getHashByEmail(String hash, String email) {
        UserCredentialsEntity entityFromRepo = repo.findById(email).get();
        if (!entityFromRepo.getHashCode().equals(hash) || !hash.startsWith("registration_")){
            throw new RuntimeException("The link has problem");
        }
        if (!entityFromRepo.getEnabled()){
            entityFromRepo.setEnabled(true);
            repo.save(entityFromRepo);
        }

    }

    @Override
    public void putUser(String email, UserCredentialsEntity entity, String hash) {
        UserCredentialsEntity entityFromRepo = repo.findById(email).get();
        if (!entityFromRepo.getHashCode().equals(hash) ||
                hash.startsWith("registration_") ||
                !entityFromRepo.getEnabled() ||
                !entity.equals(entityFromRepo)){
            throw new RuntimeException("The link has problem");
        }
        entityFromRepo.setPassword(entity.getPassword());
        entityFromRepo.setHashCode(null);
        repo.save(entityFromRepo);
    }

    @Override
    public String getHashByEmail(String email) {
        return repo.findById(email).get().getHashCode();
    }


}
