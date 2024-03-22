package no.ntnu.quized.service.registration;

import no.ntnu.quized.model.User.EmailVerificationToken;
import no.ntnu.quized.model.User.User;
import no.ntnu.quized.repository.EmailVerificationTokenRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailVerificationTokenServiceImpl implements EmailVerificationTokenService {

    private final EmailVerificationTokenRepository verificationTokenRepository;

    @Override
    public String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        EmailVerificationToken verificationToken = EmailVerificationToken.builder().token(token).user(user).build();
        verificationToken.setExpiryDate(LocalDateTime.now().plusMinutes(15));

        saveVerificationToken(verificationToken);
        return token;
    }

    @Override
    public void saveVerificationToken(EmailVerificationToken token) {
        verificationTokenRepository.save(token);
    }

    // TODO add custom exeption

    @Override
    public EmailVerificationToken getVerificationToken(String token) {
        return verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Token not found"));
    }

}
