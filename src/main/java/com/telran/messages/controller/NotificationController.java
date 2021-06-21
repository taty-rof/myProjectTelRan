package com.telran.messages.controller;

import com.telran.messages.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    public void registrationUser(String email) {
        notificationService.sendingRegistrationForm(email);
    }

    public void sendingNewPassword(String email){
        notificationService.sendingNewPassword(email);
    }
}
