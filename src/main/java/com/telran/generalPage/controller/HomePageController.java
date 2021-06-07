package com.telran.generalPage.controller;

import com.telran.generalPage.dto.Application;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class HomePageController {

    @GetMapping("applications")
    @Bean
    @ResponseStatus(code = HttpStatus.OK)
    public List<Application> getAllProductsOfCompany(){
        return List.of(Application.builder().id(123).name("Math").build());
    }
}
