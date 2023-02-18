package sa.aref.controller.accounts;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sa.aref.dto.ClientDto;
import sa.aref.entity.accounts.ClientAccount;
import sa.aref.service.accounts.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/register")
    public String register(@RequestBody ClientDto clientDto) {
        clientService.register(ClientAccount.builder()
                .firstName(clientDto.getFirstName())
                .lastName(clientDto.getLastName())
                .email(clientDto.getEmail())
                .password(clientDto.getPassword())
                .build());
        return "Register Completed";
    }

}
