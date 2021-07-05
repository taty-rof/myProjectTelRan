package com.telran.auth.controller;

import com.telran.auth.dao.entity.UserCredentialsEntity;
import com.telran.auth.dto.UserCredentialsDto;
import com.telran.auth.service.UserCredentialsService;
import com.telran.messages.controller.NotificationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import javax.validation.constraints.*;


@CrossOrigin
@RestController
@RequestMapping(value = "user")
@Validated
public class AuthController {

    private final UserCredentialsService userCredentialsService;
    private final PasswordEncoder passwordEncoder;
    private final NotificationController notificationController;

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
    public String registrationNewUser(@RequestBody @Valid UserCredentialsDto requestUserDto){

        UserCredentialsEntity entity =  UserCredentialsEntity.builder()
                .email(requestUserDto.getEmail())
                .password(passwordEncoder.encode(requestUserDto.getPassword()))
                .enabled(false)
                .roles(new String[]{"ROLE_USER"})
                .build();

        userCredentialsService.addUser(entity);
//        *** To add in production!!! ***
//        notificationController.registrationUser(requestUserDto.getEmail());
        return ""+getHashByEmail(entity.getEmail())+" The email has been sent to you. Follow the link to complete registration. ";
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("registration/{hash}")
    public void checkHash(@PathVariable @NotNull String hash, @RequestBody @Email String userEmail){
        userCredentialsService.getHash(hash,userEmail);
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{userEmail}/password/reset")
    public String passwordRecovery(@PathVariable @Email String userEmail){
        UserCredentialsEntity entity = userCredentialsService.forgetPassword(userEmail);

        if (entity!=null){
            //notificationController.sendingNewPassword(userEmail);
        }
      return ""+getHashByEmail(userEmail)+"  A temporary password has been sent to the specified mail";
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{userEmail}/password/reset/{hash}")
    public void updateUserPassword(@PathVariable @Email String userEmail,
                                   @PathVariable @NotNull String hash,
                                   @RequestBody @Valid UserCredentialsDto dto){
        if (!dto.getEmail().equals(userEmail)){
            throw new RuntimeException("You can't get this user profile");
        }
        UserCredentialsEntity entity =  UserCredentialsEntity.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();
//        userCredentialsService.putUser(entity, hash);

    }


    public String getHashByEmail(@NotNull String email){
        return userCredentialsService.getHashByEmail(email);
    }
}
