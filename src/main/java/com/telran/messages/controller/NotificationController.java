package com.telran.messages.controller;

import com.telran.auth.controller.AuthController;
import com.telran.auth.dao.UserCredentialsRepo;
import com.telran.auth.service.UserCredentialsService;
import com.telran.messages.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    private final NotificationService notificationService;
    private final AuthController authController;

    @Autowired
    public NotificationController(NotificationService notificationService,
                                  @Lazy AuthController authController) {
        this.notificationService=notificationService;
        this.authController=authController;
    }

    public void registrationUser(String email) {
        String hash = authController.getHashByEmail(email);
        notificationService.sendingRegistrationForm(email,hash);
    }

    public void sendingNewPassword(String email){
        notificationService.sendingNewPassword(email);
    }

    private String getHash(String email){
        return authController.getHashByEmail(email);
    }
}
