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
    String baseUrl = "https://telran-project.herokuapp.com";
    private String from="tatyana.donirofman@gmail.com";

    @Autowired
    public NotificationServiceImpl(JavaMailSender mailSender){
        this.mailSender=mailSender;
    }

    @Override
    public void sendingRegistrationForm(String email, String hash) {
        //String to = "lyzhina.anna@gmail.com";
        String to = email;
        String link=baseUrl+"/user/registration/"+hash;
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject("Completing registration");
        message.setSubject("Registration");
        message.setText("Please complete registration ... "+link);
        message.setSentDate(Date.valueOf(LocalDate.now()));

        mailSender.send(message);
    }

    @Override
    public void sendingNewPassword(String email) {
        //String to = "lyzhina.anna@gmail.com";
        String to = email;
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject("Forgetting password");
        message.setText("Please follow to the link to change password ... [link]");
        message.setSentDate(Date.valueOf(LocalDate.now()));

        mailSender.send(message);
    }
}
