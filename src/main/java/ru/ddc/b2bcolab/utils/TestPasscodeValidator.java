package ru.ddc.b2bcolab.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class TestPasscodeValidator implements PasscodeValidator {

    private final JavaMailSender mailSender;
    private final Map<String, String> passcodes = new HashMap<>();

    public TestPasscodeValidator(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public boolean validate(String passcode) {
        return passcodes.containsValue(passcode);
    }

    @Override
    public void sendPasscode(String email) {
        String passcode = generatePasscode();
        passcodes.put(email, passcode);
        sendEmail(email, passcode);
    }

    private String generatePasscode() {
        Random random = new Random();
        int passcode = 1000 + random.nextInt(9000);
        return String.valueOf(passcode);
    }

    private void sendEmail(String email, String passcode) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(email);
            helper.setSubject("Проверочный код");
            helper.setText("Ваш проверочный код: " + passcode);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
