package com.example.demo.service;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("rashidbekraximov21@gmail.com");
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    public void ff(){
        String recipient = "developercoder7@gmail.com";
        String subject = "Hello, Rashidbek!";
        String template = "Hello, Jallimisan !\n\n"
                + "This is a message just for you, ${firstName} ${lastName}. "
                + "We hope you're having a great day!\n\n"
                + "Best regards,\n"
                + "The Spring Boot Team";

//        Map<String, Object> variables = new HashMap<>();
//        variables.put("firstName", "John");
//        variables.put("lastName", "Doe");

        sendEmail(recipient, subject, template);
    }

}
