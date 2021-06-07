package com.telran.generalPage.controller;

import com.telran.generalPage.dto.Application;
import com.telran.generalPage.dto.MessageFromUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public void postMessageFromUser(@RequestBody MessageFromUserDto messageFromUser){
        // message from user
    }
}
