package com.hackthon.Orders.DTO;

public class EmailRequest {
    private final String to;
    private final String subject;
    private final String body;

    public EmailRequest(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    // Getters and Setters
}

