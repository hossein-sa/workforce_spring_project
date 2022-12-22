package sa.aref.service.accounts;

import org.springframework.stereotype.Service;
import sa.aref.entity.accounts.ClientAccount;
import sa.aref.repository.accounts.ClientRepository;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientAccount registerClient(ClientAccount client) {
        return clientRepository.save(client);
    }

    public void changePassword(Long id, String password) {
        clientRepository.changePassword(id, password);
    }

}
