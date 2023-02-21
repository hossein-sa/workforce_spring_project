package sa.aref.service.verification;

public interface VerificationEmailService {
    void sendVerificationEmail(String email, String token);
}
