package com.hackthon.NotificationService.controller;

import com.hackthon.NotificationService.DTO.Orders;
import com.hackthon.NotificationService.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

//        emailService.sendEmailInventoryAdmin();
        return "sent ordered EMail";
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendOrderNotification(@RequestBody Orders order) throws Exception {

        // Simulate sending a notification (e.g., email, SMS)
        String notificationMessage = "Order " + order.getOrderId() + " has been received and processed.";
        emailService.sendEmailInventoryAdmin(order.getOrderId());

        return new ResponseEntity<>("Notification Sent", HttpStatus.OK);
    }
}
