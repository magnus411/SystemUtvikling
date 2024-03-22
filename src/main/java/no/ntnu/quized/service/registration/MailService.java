package no.ntnu.quized.service.registration;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import no.ntnu.quized.model.User.User;

import org.springframework.mail.SimpleMailMessage;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    private static final String siteURL = "http://localhost:8080";

    public void sendVerificationEmail(User user, String token) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete your registration!");
        mailMessage.setFrom("noreply@your-domain.com");
        mailMessage.setText(constructEmailBody(user, token));

        mailSender.send(mailMessage);
    }

    private String constructEmailBody(User user, String token) {
        String verificationLink = siteURL + "/verify?token=" + token;
        return "Hello " + user.getUsername() + ",\n\n" +
                "Please click on the link below to verify your account:\n" +
                verificationLink + "\n\n" +
                "Thank you for registering!";
    }
}
