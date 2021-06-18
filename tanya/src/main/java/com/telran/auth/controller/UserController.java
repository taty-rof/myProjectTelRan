package com.telran.auth.controller;

import com.telran.auth.dao.UserCredentialsRepo;
import com.telran.auth.dao.entity.UserProfileEntity;
import com.telran.auth.dto.UserProfileDto;
import com.telran.auth.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("user")
@Validated
public class UserController {

    UserProfileService profileService;
    UserCredentialsRepo credentialsRepo;
    @Value("${admin}")
    String adminName;

    @Autowired
    public UserController(UserProfileService profileService,
                          UserCredentialsRepo credentialsRepo){
        this.profileService=profileService;
        this.credentialsRepo=credentialsRepo;
    }

//    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("addUser")
    @ResponseStatus(HttpStatus.CREATED)
    public String addUser(@RequestBody UserProfileDto user){
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
        String id = profileService.addUser(entity);
        credentialsRepo.addRoleStudent(entity.getEmail());
        return id;
    }

    @PutMapping("{userEmail}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@NotNull @PathVariable String userEmail,
                           @RequestBody UserProfileDto user,
                           HttpServletRequest request){
        if (!checkingUser(userEmail,request.getUserPrincipal().getName())
        && !userEmail.equals(user.getEmail())){
            throw new RuntimeException("You can't update this user profile");
        }
        UserProfileEntity entity = UserProfileEntity.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .institute(user.getInstitute())
                .degree(user.getDegree())
                .fields(user.getFields())
                .apps(user.getApps())
                .stillStudent(Boolean.TRUE)
                .build();
        profileService.updateUser(entity);
    }
//    @RolesAllowed({"ADMIN","STUDENT"})
    @GetMapping("{userEmail}")
    public UserProfileDto getUserByEmail(@NotNull @PathVariable String userEmail,
                                         HttpServletRequest request){
       if (!checkingUser(userEmail,request.getUserPrincipal().getName())){
            throw new RuntimeException("You can't get this user profile");
        }
        UserProfileEntity entity = profileService.getUser(userEmail);
        return UserProfileDto.builder()
                .id(entity.getId())
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

//**********Checking if userCredential's email equals to pathVariable email, but ADMIN can get info*************
    private Boolean checkingUser(String userEmail, String requestEmail){
        if (!requestEmail.equals(userEmail)){
            if (!requestEmail.equals(adminName)) {
                return false;
            }
        }
        return true;
    }
}
