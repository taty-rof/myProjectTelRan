package com.telran.auth.controller;

import com.telran.auth.dao.UserCredentialsRepo;
import com.telran.auth.dao.entity.UserProfileEntity;
import com.telran.auth.controller.dto.UserProfileDto;
import com.telran.auth.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.*;
import javax.validation.constraints.*;

@CrossOrigin
@RestController
@RequestMapping("user")
@Validated
public class UserController {

    private final UserProfileService profileService;
//    private final UserCredentialsRepo credentialsRepo;

    @Autowired
    public UserController(UserProfileService profileService
//                          ,UserCredentialsRepo credentialsRepo
    ){
        this.profileService=profileService;
//        this.credentialsRepo=credentialsRepo;
    }

    @PostMapping("addUser")
    @ResponseStatus(HttpStatus.CREATED)
    public String addUser(@RequestBody @Valid UserProfileDto user) {
        UserProfileEntity entity = UserProfileEntity.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .institute(user.getInstitute())
                .degree(user.getDegree())
                .fields(user.getFields())
                .apps(user.getApps())
                .stillStudent(Boolean.TRUE)
                .build();
        return profileService.addUser(entity);
    }

    @PutMapping("{userEmail}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@NotNull @PathVariable String userEmail,
                           @RequestBody UserProfileDto user,
                           HttpServletRequest request){
        UserProfileEntity entity = UserProfileEntity.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .institute(user.getInstitute())
                .degree(user.getDegree())
                .fields(user.getFields())
                .apps(user.getApps())
                .stillStudent(Boolean.TRUE)
                .build();
        profileService.updateUser(entity, userEmail, request.getUserPrincipal().getName());
    }

    @GetMapping("{userEmail}")
    public UserProfileDto getUserByEmail(@NotNull @PathVariable String userEmail,
                                         HttpServletRequest request){
        UserProfileEntity entity = profileService.getUser(userEmail,
                request.getUserPrincipal().getName());
        return UserProfileDto.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .institute(entity.getInstitute())
                .degree(entity.getDegree())
                .fields(entity.getFields())
                .apps(entity.getApps())
                .stillStudent(entity.getStillStudent())
                .build();
    }

    @DeleteMapping("{userEmail}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserByEmail(@PathVariable String userEmail){
        profileService.deleteUser(userEmail);
    }

}
