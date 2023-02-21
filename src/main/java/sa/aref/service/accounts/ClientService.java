package sa.aref.service.accounts;

import org.springframework.stereotype.Service;
import sa.aref.entity.accounts.ClientAccount;
import sa.aref.entity.verification.VerificationToken;
import sa.aref.exception.CustomExceptionIsExist;
import sa.aref.exception.CustomExceptionNotFound;
import sa.aref.repository.accounts.ClientRepository;
import sa.aref.repository.verification.VerificationTokenRepository;
import sa.aref.service.verification.VerificationEmailService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final VerificationEmailService verificationEmailService;
    private static final Logger logger = LogManager.getLogger(ClientService.class);



    public ClientService(ClientRepository clientRepository, VerificationTokenRepository verificationTokenRepository, VerificationEmailService verificationEmailService) {
        this.clientRepository = clientRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.verificationEmailService = verificationEmailService;
    }

    public void register(ClientAccount clientAccount) {
        if (clientRepository.existsByEmail(clientAccount.getEmail()))
            throw new CustomExceptionIsExist("This email account is exist");
        clientRepository.save(clientAccount);
        logger.info("Saved new client account: {}", clientAccount.getEmail());

        // Generate a verification token
        String token = UUID.randomUUID().toString();

        // Save the verification token
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setClientAccount(clientAccount);
        verificationToken.setToken(token);
        verificationToken.setExpiryDate(LocalDateTime.now().plusDays(1));
        verificationTokenRepository.save(verificationToken);

        // Send a verification email
        verificationEmailService.sendVerificationEmail(clientAccount.getEmail(), token);
    }
    public void changePassword(Integer clientId, String newPassword) {
        Optional<ClientAccount> optionalClientAccount = clientRepository.findById(Long.valueOf(clientId));
        if (optionalClientAccount.isPresent()) {
            ClientAccount clientAccount = optionalClientAccount.get();
            clientAccount.setPassword(newPassword);
            clientRepository.save(clientAccount);
            logger.info("Password updated for client with ID: {}", clientId);
        } else {
            throw new CustomExceptionNotFound("Client not found with ID: " + clientId);
        }
    }

}
