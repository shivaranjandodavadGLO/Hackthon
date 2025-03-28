package com.hackthon.NotificationService.controller;

import com.hackthon.NotificationService.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    // Accepts JSON request body instead of request parameters
    @GetMapping("/email")
    public String getEmailService() throws Exception {
        emailService.sendEmail();

        return "sent EMail";
    }

    @GetMapping("/emailorder")
    public String getEmailServicePurchase() throws Exception {

        emailService.sendEmailInventoryAdmin();
        return "sent ordered EMail";
    }
}
