package sa.aref.controller.accounts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sa.aref.dto.ChangeClientPasswordDto;
import sa.aref.dto.ClientDto;
import sa.aref.entity.accounts.ClientAccount;
import sa.aref.exception.CustomExceptionInvalid;
import sa.aref.exception.CustomExceptionIsExist;
import sa.aref.exception.CustomExceptionNotFound;
import sa.aref.service.accounts.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody ClientDto clientDto) {
        try {
            clientService.register(ClientAccount.builder()
                    .firstName(clientDto.getFirstName())
                    .lastName(clientDto.getLastName())
                    .email(clientDto.getEmail())
                    .password(clientDto.getPassword())
                    .build());
            return ResponseEntity.ok("Registration completed successfully");
        } catch (CustomExceptionIsExist e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangeClientPasswordDto changeClientPasswordDto) {
        try {
            clientService.changePassword((int) changeClientPasswordDto.getClientId(), changeClientPasswordDto.getCurrentPassword(), changeClientPasswordDto.getNewPassword());
            return ResponseEntity.ok("Password changed successfully");
        } catch (CustomExceptionNotFound | CustomExceptionInvalid e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
