package com.telran.auth.controller;

import com.telran.auth.dao.UserCredentialsEntity;
import com.telran.auth.dao.UserCredentialsRepo;
import com.telran.auth.dao.UserProfileEntity;
import com.telran.auth.dto.UserProfileDto;
import com.telran.auth.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

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
//    public ResponseSuccessDto
    public void updateUser(@RequestBody @PathVariable String userEmail){
        //
    }
//    @RolesAllowed({"ADMIN","STUDENT"})
    @GetMapping("{userEmail}")
    public UserProfileDto getUserByEmail(@NotNull @PathVariable String userEmail,
                                         HttpServletRequest request){
//**********Checking if userCredential's email equals to pathVariable email, but ADMIN can get info*************
        if (!request.getUserPrincipal().getName().equals(userEmail)){
            System.out.println(request.getUserPrincipal().getName());
            System.out.println(adminName);
            if (!request.getUserPrincipal().getName()
                    .equals(adminName)) {
                throw new RuntimeException("You can't get this user profile");
            }
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
//    public ResponseSuccessDto
    public void deleteUserByEmail(@RequestBody @PathVariable String userEmail){
        //todo
    }
}
