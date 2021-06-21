package com.telran.messages.service;

public interface NotificationService {
    void sendingRegistrationForm(String email);
    void sendingNewPassword(String email);
}
