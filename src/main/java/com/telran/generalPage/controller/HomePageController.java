package com.telran.generalPage.controller;

import com.telran.generalPage.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@RestController
public class HomePageController<UserRepository> {


    @GetMapping("applications")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Application> getAllProductsOfCompany(){
        return List.of(Application.builder().id(123).name("Math").build());
    }

    @PostMapping("/message")
    @ResponseStatus(code = HttpStatus.OK)
        public void postMessageFromUser(@Valid @RequestBody MessageFromUserDto messageFromUser){
        // message from user
    }
}
