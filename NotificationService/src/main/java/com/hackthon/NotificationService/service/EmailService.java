package com.hackthon.NotificationService.service;

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

    public void sendEmailInventoryAdmin(String orderId) throws Exception {
//        String subject = "Order Confirmation - " + orderId;
        String subject = "Order Confirmation -SHIV_ORDER 12344252525" + orderId;
        // Build the email content

        String emailContent = "Thank You for Your Purchase, " + "customerName" + "!\n\n" +
                "Your order has been confirmed. Here are the details:\n\n" +
                "Order Details:\n" +
                "Order ID: " + "orderId" + "\n" +
//        emailContent.append("Order Date: ").append(dateFormat.format(orderDate)).append("\n\n");
                "Items Purchased:\n" +

//        for (String item : items) {
//            emailContent.append("- ").append(item).append("\n");
//        }

                "\nTotal Amount: $" + "totalamount" + "\n\n" +
                "We appreciate your business and hope to serve you again soon!\n\n" +
                "Best Regards,\n" +
                "Your Company Name";


        emailSenderService.SendEmail("shivaranjan.dodavad@gmail.com", subject, emailContent);
    }

}

