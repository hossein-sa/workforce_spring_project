package sa.aref.service.accounts;

import org.springframework.stereotype.Service;
import sa.aref.entity.accounts.ClientAccount;
import sa.aref.exception.CustomExceptionIsExist;
import sa.aref.repository.accounts.ClientRepository;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void register(ClientAccount clientAccount) {
        if (clientRepository.existsByEmail(clientAccount.getEmail()))
            throw new CustomExceptionIsExist("This email account is exist");
        clientRepository.save(clientAccount);
    }

}
