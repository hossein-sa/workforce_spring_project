package sa.aref.service.verification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class VerificationEmailServiceImpl implements VerificationEmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.mail.sender}")
    private String sender;

    @Value("${app.mail.subject}")
    private String subject;

    @Value("${app.mail.content}")
    private String content;

    @Override
    public void sendVerificationEmail(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(content + " http://localhost:8080/verify-email?token=" + token);
        message.setFrom(sender);
        mailSender.send(message);
    }
}
