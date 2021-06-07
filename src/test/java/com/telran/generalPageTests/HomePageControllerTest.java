package com.telran.generalPageTests;

import com.telran.generalPage.controller.HomePageController;
import com.telran.generalPage.dto.Application;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class HomePageControllerTest {

    @Autowired
    HomePageController controller;


 /*   @BeforeAll
    public static void setUp() {
        RestTemplate restTemplate = new RestTemplate();
    }*/

    String baseUrl = "http://localhost:8080";

    @Test
    public void homePage_shouldReturnListOfApplications_getAllProductsOfCompany(){
        List<Application> list = controller.getAllProductsOfCompany();
        List<Application> expectedList = List.of(Application.builder().id(123).name("Math").build());
        assertNotNull(list);
        assertEquals(expectedList.size(), list.size() );
        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i),expectedList.get(i));
        }
    }
}
