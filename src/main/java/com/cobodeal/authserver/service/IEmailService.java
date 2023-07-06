package com.cobodeal.authserver.service;

public interface IEmailService {

    public void sendEmail(String to, String subject, String text);
}
