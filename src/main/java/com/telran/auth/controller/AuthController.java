package com.telran.auth.controller;

import com.telran.auth.dao.UserEntity;
import com.telran.auth.dao.UserRepo;
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

    UserRepo userRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserRepo userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("registration")
    public String registrationNewUser(@RequestBody UserCredentialsDto requestUserDto){

        UserEntity entity =  UserEntity.builder()
                .username(requestUserDto.getEmail())
                .password("{noop}123"+requestUserDto.getPassword())
                .roles(new String[]{"ROLE_USER"})
                .build();

        userRepository.addUser(requestUserDto.getEmail(),entity);

        return "The email has been sent to you. Follow the link to complete registration.";
    }

//    @GetMapping("auth/registration/{hash}")     ???????

    @GetMapping("auth/login")
    public void loginUser(){ //???
        // returns token
    }

    @GetMapping("auth/logout")
    public void logoutUser(){ // ??
        //to do
    }

    @GetMapping("user/{userEmail}/password/reset")
    public void passwordRecovery(@PathVariable String userEmail){
        //to do
    }

    @PutMapping("user/{userEmail}/password/reset")
    public void updateUserPassword (@PathVariable String userEmail){
        // to do
    }

}
