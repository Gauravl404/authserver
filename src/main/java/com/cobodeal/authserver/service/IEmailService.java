package com.cobodeal.authserver.service;

public interface IEmailService {

    void sendEmail(String to, String subject, String text);
}
