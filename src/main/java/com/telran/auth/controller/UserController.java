package com.telran.auth.controller;

import com.telran.auth.dto.UserProfileDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
//@Validated
public class UserController {
//    repository

    @PostMapping("user/addUser")
    public void addUser(UserProfileDto user){
        //todo
    }

    @PutMapping("user/{userEmail}")
//    public ResponseSuccessDto
    public void updateUser(@RequestBody @PathVariable String userEmail){
        //
    }

    @GetMapping("user/{userEmail}")
    public UserProfileDto getUserByEmail(@RequestBody @PathVariable String userEmail){
        return null;
    }

    @DeleteMapping("user/{userEmail}")
//    public ResponseSuccessDto
    public void deleteUserByEmail(@RequestBody @PathVariable String userEmail){
        //todo
    }
}
