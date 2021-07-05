package com.telran.auth.dao;

import com.telran.auth.dao.entity.*;
import org.springframework.data.jpa.repository.*;

public interface UserCredentialsRepo extends JpaRepository<UserCredentialsEntity,String> {
/*    void addUser(UserCredentialsEntity user);
    UserCredentialsEntity getUser(String email);
    void addRoleStudent(String email);
    void checkHash(String hash, String userEmail);
    String getHashByEmail(String userEmail);
    UserCredentialsEntity postHashIfForgetPassword(String email);
    void putUser(UserCredentialsEntity entity, String hash);
*/
    //UserHashEntity findUserHashEntityByEmail(String email);
    //UserCredentialsEntity findUserCredentialsEntityByEmail(String email);
}
