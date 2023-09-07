package com.pokedex.mailservice.service;

import com.pokedex.mailservice.dto.ForgotPasswordEmailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailSenderService {
    private final JavaMailSender mailSender;

    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @KafkaListener(topics = "emailForgotPasswordTopic")
    public void sendForgotPasswordEmail(ForgotPasswordEmailDto dto) {
        log.info("Received forgot password send email request");
        String context = "Dear " + dto.getName() + ",\n\n" +
                "Your password has been reset for security reasons. \nYour new password is: " + dto.getNewPassword() +
                "\n\nWe recommend changing your password as soon as possible to ensure the safety of your account. " +
                "Please create a strong and unique password consisting of a combination of uppercase and lowercase letters, numbers,"
                +
                " and special characters. If you did not request a password reset, please contact our customer support team immediately. "
                +
                "\n\nThank you for choosing our service. \n\nBest regards,\n\nPokedex ";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Iceshu4cyberbullies@gmail.com");
        message.setTo(dto.getToEmail());
        message.setText(context);
        message.setSubject("Your New Pokedex Account Password");
        mailSender.send(message);
    }


}
