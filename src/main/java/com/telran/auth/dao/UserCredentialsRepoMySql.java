package com.telran.auth.dao;

import com.telran.auth.dao.entity.UserCredentialsEntity;
import com.telran.auth.dao.entity.UserHashEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public class UserCredentialsRepoMySql implements UserCredentialsRepo{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void addUser(UserCredentialsEntity user) {
        UserCredentialsEntity entity = entityManager.find(UserCredentialsEntity.class,user.getUsername());
        if(entity == null) {
            UserHashEntity hashEntity = UserHashEntity.builder()
                    .email(user.getUsername())
                    .hash(UUID.randomUUID().toString())
                    .build();
            user.setHashCode(hashEntity);
            entityManager.persist(user);
        }else{
            throw new RuntimeException(String.format("User with username: %s already exists!", user.getUsername()));
        }
    }

    @Override
    public UserCredentialsEntity getUser(String email) {
        UserCredentialsEntity entity = entityManager.find(UserCredentialsEntity.class,email);
        if(entity == null) {
            throw new RuntimeException(String.format("User with username: %s doesn't exists!", email));
        }
        return entity;
    }

    @Override
    public void addRoleStudent(String email) {
        UserCredentialsEntity entity = entityManager.find(UserCredentialsEntity.class,email);
        if(entity == null) {
            throw new RuntimeException(String.format("User with username: %s doesn't exists!", email));
        }
        entity.setEnabled(true);
        entity.setRoles(new String[]{"ROLE_USER","ROLE_STUDENT"});
        UserHashEntity hashEntity = UserHashEntity.builder()
                .email(email)
                .build();
        entityManager.persist(entity);
        entityManager.remove(hashEntity);
    }

    @Override
    public void checkHash(String hash, String userEmail) {
        UserHashEntity entity = entityManager.find(UserHashEntity.class,userEmail);
        if (!(entity.getHash().equals(hash))) {
            throw new RuntimeException(String.format("User with username: %s doesn't exist!", userEmail));
        }
    }

    @Override
    public String getHashByEmail(String userEmail) {
        UserHashEntity entity = entityManager.find(UserHashEntity.class,userEmail);
        return entity.getHash();
    }

    @Override
    public UserCredentialsEntity postHashIfForgetPassword(String email) {
        UserCredentialsEntity entity = entityManager.find(UserCredentialsEntity.class,email);
        if(entity != null) {
            UserHashEntity hashEntity = UserHashEntity.builder()
                    .email(email)
                    .hash(UUID.randomUUID().toString())
                    .build();
            hashEntity.setUser(entity);
            hashEntity.setHash(UUID.randomUUID().toString());
            entityManager.persist(hashEntity);
        }
        throw new RuntimeException(String.format("User with username: %s doesn't exists!", email));

    }

    @Override
    public void putUser(UserCredentialsEntity entity, String hash) {

    }
}
