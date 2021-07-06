package com.telran.auth.dao;

import com.telran.auth.dao.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserPofileRepo extends JpaRepository<UserProfileEntity,String> {
}
