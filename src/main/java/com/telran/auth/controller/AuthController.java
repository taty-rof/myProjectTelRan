package com.telran.auth.controller;

import com.telran.auth.dao.UserCredentialsEntity;
import com.telran.auth.dao.UserCredentialsRepo;
import com.telran.auth.dto.UserCredentialsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user")
@Validated
public class AuthController {

    UserCredentialsRepo userCredentialsRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserCredentialsRepo userCredentialsRepository, PasswordEncoder passwordEncoder) {
        this.userCredentialsRepository = userCredentialsRepository;
        this.passwordEncoder=passwordEncoder;
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("registration")
    public String registrationNewUser(@RequestBody UserCredentialsDto requestUserDto){

        UserCredentialsEntity entity =  UserCredentialsEntity.builder()
                .username(requestUserDto.getEmail())
                .password(passwordEncoder.encode(requestUserDto.getPassword()))
                .roles(new String[]{"ROLE_USER"})
                .build();

        userCredentialsRepository.addUser(requestUserDto.getEmail(),entity);

        return "The email has been sent to you. Follow the link to complete registration.";
    }

//    @GetMapping("auth/registration/{hash}")     ???????

    @GetMapping("login")
    public void loginUser(){ //???
        // returns token
    }

    @GetMapping("logout")
    public void logoutUser(){ // ??
        //to do
    }

    @GetMapping("{userEmail}/password/reset")
    public void passwordRecovery(@PathVariable String userEmail){
        //to do
    }

    @PutMapping("{userEmail}/password/reset")
    public void updateUserPassword (@PathVariable String userEmail){
        // to do
    }

}
