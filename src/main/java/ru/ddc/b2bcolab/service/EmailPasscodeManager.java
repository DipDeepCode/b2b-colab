package ru.ddc.b2bcolab.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.ddc.b2bcolab.model.Customer;
import ru.ddc.b2bcolab.utils.PasscodeGenerator;

@Service
@RequiredArgsConstructor
public class EmailPasscodeManager implements PasscodeManager {
    private final JavaMailSender mailSender;
    private final PasscodeGenerator passcodeGenerator;
    private final HttpSession httpSession;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Override
    public void sendPasscode(Customer customer) {
        String passcode = passcodeGenerator.generate();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom(senderEmail);
            helper.setTo(customer.getEmail());
            helper.setSubject("Проверочный код для регистрации на w2w match");
            helper.setText("Ваш проверочный код: " + passcode);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }

        httpSession.setAttribute("passcode", passcode);
    }

    @Override
    public boolean verifyPasscode(String passcode) {
        return passcode.equals(httpSession.getAttribute("passcode"));
    }
}
