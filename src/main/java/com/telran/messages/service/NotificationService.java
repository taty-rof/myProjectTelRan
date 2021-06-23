package com.telran.messages.service;

public interface NotificationService {
    void sendingRegistrationForm(String email, String hash);
    void sendingNewPassword(String email);
}
