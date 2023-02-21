package sa.aref.service.accounts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sa.aref.entity.accounts.ClientAccount;
import sa.aref.exception.CustomExceptionIsExist;
import sa.aref.repository.accounts.ClientRepository;
import sa.aref.repository.verification.VerificationTokenRepository;
import sa.aref.service.verification.VerificationEmailService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @Mock
    private VerificationTokenRepository verificationTokenRepository;

    @Mock
    private VerificationEmailService verificationEmailService;

    @Test
    public void testRegister() {
        // Create a new client account
        ClientAccount clientAccount = new ClientAccount();
        clientAccount.setEmail("test@example.com");
        clientAccount.setPassword("password");

        // Set up mock behavior
        when(clientRepository.existsByEmail(any())).thenReturn(false);
        when(clientRepository.save(any(ClientAccount.class))).thenReturn(clientAccount);
        doNothing().when(verificationEmailService).sendVerificationEmail(anyString(), anyString());

        // Call the method being tested
        ClientService clientService = new ClientService(clientRepository, verificationTokenRepository, verificationEmailService);
        clientService.register(clientAccount);

        // Verify that the expected methods were called
        verify(clientRepository, times(1)).existsByEmail(any());
        verify(clientRepository, times(1)).save(any(ClientAccount.class));
        verify(verificationTokenRepository, times(1)).save(any());
        verify(verificationEmailService, times(1)).sendVerificationEmail(anyString(), anyString());
    }

    @Test
    public void testRegisterWithExistingEmail() {
        // Create a new client account
        ClientAccount clientAccount = new ClientAccount();
        clientAccount.setEmail("test@example.com");
        clientAccount.setPassword("password");

        // Set up mock behavior
        when(clientRepository.existsByEmail(any())).thenReturn(true);

        // Call the method being tested
        ClientService clientService = new ClientService(clientRepository, verificationTokenRepository, verificationEmailService);
        assertThrows(CustomExceptionIsExist.class, () -> {
            clientService.register(clientAccount);
        });

        // Verify that the expected methods were called
        verify(clientRepository, times(1)).existsByEmail(any());
        verify(clientRepository, never()).save(any(ClientAccount.class));
        verify(verificationTokenRepository, never()).save(any());
        verify(verificationEmailService, never()).sendVerificationEmail(anyString(), anyString());
    }

}