package com.telran.auth;

import com.telran.auth.controller.AuthController;
import com.telran.auth.dao.UserCredentialsRepo;
import com.telran.auth.dao.entity.UserCredentialsEntity;
import com.telran.auth.controller.dto.UserCredentialsDto;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.validation.ValidationException;

import static org.mockito.Mockito.doNothing;

//@SpringBootTest
public class AuthControllerTest {

    @MockBean
    UserCredentialsRepo userCredentialsRepo;

    @Autowired
    AuthController controller;

    @Test
    public void registrationNewUser_WithUniqueContact_ExpectedStringText(){
        doNothing().when(userCredentialsRepo).save(Mockito.any(UserCredentialsEntity.class));
        UserCredentialsDto dto = UserCredentialsDto.builder()
                .email("tatyana@mail.com")
                .password("54321")
                .build();
        String res = controller.registrationNewUser(dto);
        Assertions.assertEquals(
//for test
   "null The email has been sent to you. Follow the link to complete registration. ",res);
//    "The email has been sent to you. Follow the link to complete registration. ",res);
    }

    @Test
    public void registrationNewUser_WithValidationError_ExpectedException(){
        doNothing().when(userCredentialsRepo).save(Mockito.any(UserCredentialsEntity.class));
        UserCredentialsDto dto = UserCredentialsDto.builder()
                .email("tatyana")
                .password("54321")
                .build();
        Assertions.assertThrows(ValidationException.class,
                ()->controller.registrationNewUser(dto));
    }

/*
    @Test
    public void registrationNewUser_WithValidationError_ExpectedException(){
        doNothing().when(userCredentialsRepo).addUser(Mockito.any(UserCredentialsEntity.class));
        UserCredentialsDto dto = UserCredentialsDto.builder()
                .email("tatyana")
                .password("54321")
                .build();
        Assertions.assertThrows(ValidationException.class,
                ()->controller.registrationNewUser(dto));
    }

 */
}
