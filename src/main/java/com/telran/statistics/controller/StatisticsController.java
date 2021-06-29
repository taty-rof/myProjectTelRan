package com.telran.statistics.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.*;

@CrossOrigin
@RestController
@RequestMapping(value = "statistics")
@Validated
public class StatisticsController {

    @Value("${admin}")
    private String adminName;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{userEmail}")
    public String getUserStatisticsByEmail(@PathVariable @Email String userEmail,
                                           HttpServletRequest request ){

        return "{\n" +
                "  \"id\": \"string\",\n" +
                "  \"applications\": [\n" +
                "    {\n" +
                "      \"itemId\": 0,\n" +
                "      \"progress\": 0\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{userEmail}/{appId}")
    public String getUserStatisticsByApplicationId(@PathVariable @Email String userEmail,
                                                  @PathVariable @NotNull String appId,
                                                    HttpServletRequest request ){

        return "{\n" +
                "  \"id\": 0,\n" +
                "  \"theory\": [\n" +
                "    {\n" +
                "      \"itemId\": 0,\n" +
                "      \"progress\": 0\n" +
                "    }\n" +
                "  ],\n" +
                "  \"exercises\": [\n" +
                "    {\n" +
                "      \"exId\": 0,\n" +
                "      \"level\": 0,\n" +
                "      \"progress\": 0,\n" +
                "      \"paragraphsOverall\": 0\n" +
                "    }\n" +
                "  ],\n" +
                "  \"exam\": true\n" +
                "}";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{userEmail}/{appId}/theory")
    public String getTheoryStatisticsByApplicationId(@PathVariable @Email String userEmail,
                                                   @PathVariable @NotNull String appId,
                                                   HttpServletRequest request ){
        if (!checkingUser(userEmail,request.getUserPrincipal().getName())){
            throw new RuntimeException("You can't get this user profile");
        }
        return "{\n" +
                "  \"itemId\": 0,\n" +
                "  \"progress\": 0\n" +
                "}";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{userEmail}/{appId}/exercises")
    public String getExercisesStatisticsByApplicationId(@PathVariable @Email String userEmail,
                                                     @PathVariable @NotNull String appId,
                                                     HttpServletRequest request ){
        return "{\n" +
                "  \"exId\": 0,\n" +
                "  \"level\": 0,\n" +
                "  \"progress\": 0,\n" +
                "  \"paragraphsOverall\": 0\n" +
                "}";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{userEmail}/{appId}/exam")
    public String getExamStatisticsByApplicationId(@PathVariable @Email String userEmail,
                                                        @PathVariable @NotNull String appId,
                                                        HttpServletRequest request ){
        return "{\n" +
                "  \"examId\": 0,\n" +
                "  \"gradeOverall\": 0,\n" +
                "  \"exercises\": [\n" +
                "    {\n" +
                "      \"exId\": 0,\n" +
                "      \"level\": 0,\n" +
                "      \"progress\": 0,\n" +
                "      \"paragraphsOverall\": 0\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    private Boolean checkingUser(String userEmail, String requestEmail){
        if (!requestEmail.equals(userEmail)){
            if (!requestEmail.equals(adminName)) {
                return false;
                //add exception
            }
        }
        return true;
    }
}
