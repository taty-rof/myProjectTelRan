package com.telran.generalPage;

import com.telran.generalPage.controller.HomePageController;
import com.telran.generalPage.dto.ApplicationDto;
import com.telran.generalPage.dto.MessageFromUserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    public void noInputValues_shouldReturnListOfApplications_getAllProductsOfCompany(){
        List<ApplicationDto> list = controller.getAllProductsOfCompany();
        List<ApplicationDto> expectedList = List.of(ApplicationDto.builder().id(123).name("Math").build());
        assertNotNull(list);
        assertEquals(expectedList.size(), list.size() );
        for (int i = 0; i < list.size(); i++) {
            Assertions.assertEquals(list.get(i),expectedList.get(i));
        }
    }

    @Test
    public void postCorrectMessageFromUerDto_shouldReturnStatusOkWithoutErrors_postMessageFromUser(){
        MessageFromUserDto message = MessageFromUserDto.builder()
                .message("Good products!")
                .email("jack_k@gmail.com")
                .fullName("Jack K")
                .build();
        controller.postMessageFromUser(message);
        // We check a new message in repository
        //assertTrue if exists in repository
    }

    @Test
    public void postIncorrectMessageMessageFromUerDto_shouldReturnValidationException_postMessageFromUser(){
        MessageFromUserDto message = MessageFromUserDto.builder()
                //.message("")
                .email("jack_k@gmail.com")
                .fullName("Jack K")
                .build();

        //At first - to add exception

//        try{
//            controller.postMessageFromUser(message);
//        }catch( exception){
//            System.out.println("error "+exception.getMessage());
        }
    @Test
    public void postIncorrectEmailMessageFromUerDto_shouldReturnValidationException_postMessageFromUser(){
        MessageFromUserDto message = MessageFromUserDto.builder()
                .message("Good products!")
                .email("jack_k@gmail")
                .fullName("Jack K")
                .build();

        //At first - to add exception

//        try{
//            controller.postMessageFromUser(message);
//        }catch( exception){
//            System.out.println("error "+exception.getMessage());
    }

    @Test
    public void postIncorrectNameMessageFromUerDto_shouldReturnValidationException_postMessageFromUser(){
        MessageFromUserDto message = MessageFromUserDto.builder()
                .message("Good products!")
                .email("jack_k@gmail")
//                .fullName("Jack K")
                .build();

        //At first - to add exception

//        try{
//            controller.postMessageFromUser(message);
//        }catch( exception){
//            System.out.println("error "+exception.getMessage());
    }
}
