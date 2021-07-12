package com.telran.statistics.controller;

import com.telran.statistics.service.StatisticsService;
import com.telran.statistics.service.model.TheoryModel;
import org.springframework.beans.factory.annotation.Autowired;
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

    final private StatisticsService service;

    @Autowired
    public StatisticsController(StatisticsService service){
        this.service=service;
    }

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
        TheoryModel model = TheoryModel.builder()
                .email(userEmail)
                .itemId(appId)
                .requestEmail(request.getUserPrincipal().getName())
                .build();
        return service.getTheoryStatistics(model);
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

}
