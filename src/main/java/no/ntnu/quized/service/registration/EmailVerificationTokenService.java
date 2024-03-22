package no.ntnu.quized.service.registration;

import no.ntnu.quized.model.User.EmailVerificationToken;
import no.ntnu.quized.model.User.User;

public interface EmailVerificationTokenService {
    String generateVerificationToken(User user);

    void saveVerificationToken(EmailVerificationToken token);

    EmailVerificationToken getVerificationToken(String token);
    
}