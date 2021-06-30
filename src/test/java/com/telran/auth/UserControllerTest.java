package com.telran.auth;

import com.telran.auth.controller.AuthController;
import com.telran.auth.dao.UserCredentialsRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.*;

@SpringBootTest
public class UserControllerTest {

    @MockBean
    UserCredentialsRepo userCredentialsRepo;



}
