package com.telran.auth.controller;

import com.telran.auth.dto.UserCredentialsDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "auth")
//@Validated
public class AuthController {
    // UserRepository


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("auth/registration")
    public void registrationNewUser(UserCredentialsDto user){
        //to do
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
    public void updateUerPassword (@PathVariable String userEmail){
        // to do
    }

}
