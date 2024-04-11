package no.ntnu.sysDev.service.registration;

import no.ntnu.sysDev.model.User.EmailVerificationToken;
import no.ntnu.sysDev.model.User.User;

public interface EmailVerificationTokenService {
    String generateVerificationToken(User user);

    void saveVerificationToken(EmailVerificationToken token);

    EmailVerificationToken getVerificationToken(String token);

}