package com.telran.messages.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.mail.*;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class NotificationServiceImpl implements NotificationService{

    private JavaMailSender mailSender;

    @Autowired
    public NotificationServiceImpl(JavaMailSender mailSender){
        this.mailSender=mailSender;
    }

    @Override
    public void sendingRegistrationForm(String email) {
        String from = "tatyana.donirofman@gmail.com";
        //String to = "lyzhina.anna@gmail.com";
        String to = "tatyana.donirofman@gmail.com";
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject("This is a link to complete registration");
        message.setText("Please complete registration ... [link]");
        message.setSentDate(Date.valueOf(LocalDate.now()));

        System.out.println(message+" MESSAGE TO ME");
        mailSender.send(message);
    }
}
