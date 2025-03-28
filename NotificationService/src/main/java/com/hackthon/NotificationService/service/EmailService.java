package com.hackthon.NotificationService.service;

import com.hackthon.NotificationService.DTO.Orders;
import com.hackthon.NotificationService.model.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail() throws Exception {
        emailSenderService.SendEmail("shivaranjan.dodavad@gmail.com", "subject", "body");
    }

    public void sendEmailInventoryAdmin(Orders orders) throws Exception {
//        String subject = "Order Confirmation - " + orderId;
        String subject = "Order Confirmation - " + orders.getOrderId();
        // Build the email content

        String emailContent = "Thank You for Your Purchase, " + "customerName" + "!\n\n" +
                "Your order has been confirmed. Here are the details:\n\n" +
                "Order Details:\n" +
                "Order ID: " + orders.getOrderId() + "\n" +
//        emailContent.append("Order Date: ").append(dateFormat.format(orderDate)).append("\n\n");

//        for (String item : items) {
//            emailContent.append("- ").append(item).append("\n");
//        }

                "\nTotal Amount: $" + orders.getTotalAmount() + "\n\n" +
                "Payment Completed\n" +
                "We appreciate your business and hope to serve you again soon!\n\n" +
                "Best Regards,\n" +
                "Team Data Avengers";


        emailSenderService.SendEmail("shivaranjan.dodavad@gmail.com", subject, emailContent);
    }

}

