package com.telran.auth.controller;

import com.telran.auth.dao.entity.UserCredentialsEntity;
import com.telran.auth.dao.UserCredentialsRepo;
import com.telran.auth.dto.UserCredentialsDto;
import com.telran.auth.service.UserCredentialsService;
import com.telran.messages.controller.NotificationController;
import com.telran.messages.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user")
@Validated
public class AuthController {

    UserCredentialsService userCredentialsService;
    PasswordEncoder passwordEncoder;
    NotificationController notificationController;

    @Autowired
    public AuthController(UserCredentialsService userCredentialsService,
                          PasswordEncoder passwordEncoder,
                          NotificationController notificationController) {
        this.userCredentialsService = userCredentialsService;
        this.passwordEncoder=passwordEncoder;
        this.notificationController=notificationController;
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("registration")
    public String registrationNewUser(@RequestBody UserCredentialsDto requestUserDto){

        UserCredentialsEntity entity =  UserCredentialsEntity.builder()
                .username(requestUserDto.getEmail())
                .password(passwordEncoder.encode(requestUserDto.getPassword()))
                .roles(new String[]{"ROLE_USER"})
                .build();

        userCredentialsService.addUser(entity);
        notificationController.registrationUser(requestUserDto.getEmail());
        return "The email has been sent to you. Follow the link to complete registration.";
    }

//    @GetMapping("auth/registration/{hash}")     ???????


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{userEmail}/password/reset")
    public void passwordRecovery(@PathVariable String userEmail){
        userCredentialsService.addUser(entity);
        notificationController.registrationUser(requestUserDto.getEmail());
    }

    @PutMapping("{userEmail}/password/reset")
    public void updateUserPassword (@PathVariable String userEmail){
        // to do
    }

}
