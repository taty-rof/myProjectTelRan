package com.telran.auth.dao;

import com.telran.auth.dao.entity.*;
import org.springframework.data.jpa.repository.*;

public interface UserCredentialsRepo extends JpaRepository<UserCredentialsEntity,String> {

}
