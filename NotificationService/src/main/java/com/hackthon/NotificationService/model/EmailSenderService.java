package com.hackthon.NotificationService.model;


import com.hackthon.NotificationService.util.EmailConst;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>EmailSenderService</h1>
 * <h4>Service Layer</h4>
 * EmailSenderService
 * <h5>
 * Email Sending Class
 * </h5>
 *
 * @author Shivarajan.Dodavad
 * @version 1.0
 * @since 2022-12-29
 */
@Service
public class EmailSenderService {
    private static final Logger logs = LoggerFactory.getLogger(EmailSenderService.class);
    /*
    Java Mail Sender opjects to Send Emails
     */
    @Autowired
    private JavaMailSender mailsender;

    public EmailSenderService() {
        try {
            URL url = new URL("https://firebasestorage.googleapis.com/v0/b/email-23ba8.appspot.com/o/logo.png?alt=media&token=75b9fd54-f338-4a7a-9984-da4a1c92594d");
            InputStream in = url.openStream();
            Files.copy(in, Paths.get("logo.png"), StandardCopyOption.REPLACE_EXISTING);
            in.close();
        } catch (IOException e) {
            throw new RuntimeException("logo not found");
        }
    }

    private static boolean isValidEmail(String email) {
        logs.info("email Validating....");
        Pattern emailPattern = Pattern.compile(EmailConst.MAIL_PATTERN_REGEX);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();

    }

    //Validating Email
    private static void validateEmail(String toEmail) throws Exception {
        logs.info("Validate toEmail Email");
        if (!isValidEmail(toEmail)) {
            logs.warn("Email is invalid");
            throw new Exception(EmailConst.EMAIL_INVALID + toEmail);
        } else {
            logs.info("Email is valid");
        }
    }

    public void SendEmail(String toEmail, String stduentSubject, String Body) throws Exception {

        try {
			/*
			Validate Emails if invalid then through errors
			 */

            logs.info("Configuring    Email for Sending.......");

            validateEmail(toEmail);


            logs.info("Mail Details Configuring....");
            // mail sender Details
            MimeMessage message = mailsender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject(stduentSubject);

            helper.setFrom(EmailConst.FROM_MAIL);
            helper.setTo(toEmail);
            helper.setText(Body);


            File image = new File("logo.png");
            FileSystemResource imageResource = new FileSystemResource(image);
            helper.addInline("imageId", imageResource);

            logs.info("Mail Details Configured");

			/*
			Threading used here to run indvidual process  sending mail individually w.r.t
			At Faster Speed
			 */


            Thread SendingMailThread = new Thread() {
                public void run() {
                    logs.info("Email Sending....");
                    mailsender.send(message);
                    logs.info("Email Sent Successfully");
                }

            };


            // MailS Send
            logs.info("Thread Process Started");
            SendingMailThread.start();

        } catch (Exception ex) {
            logs.error("Error While Sending Email " + ex);

            throw new Exception(EmailConst.EMAIL_INVALID + ex);
        }
    }


}
