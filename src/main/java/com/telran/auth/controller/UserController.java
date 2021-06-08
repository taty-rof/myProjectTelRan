package com.telran.auth.controller;

import com.telran.auth.dto.UserProfile;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
//@Validated
public class UserController {
//    repository

    @PostMapping("user/addUser")
    public void addUser(UserProfile user){
        //todo
    }

    @PutMapping("user/{userEmail}")
//    public ResponseSuccessDto
    public void updateUser(@RequestBody @PathVariable String userEmail){
        //
    }

    @GetMapping("user/{userEmail}")
    public UserProfile getUserByEmail(@RequestBody @PathVariable String userEmail){
        return null;
    }

    @DeleteMapping("user/{userEmail}")
//    public ResponseSuccessDto
    public void deleteUserByEmail(@RequestBody @PathVariable String userEmail){
        //todo
    }
}
