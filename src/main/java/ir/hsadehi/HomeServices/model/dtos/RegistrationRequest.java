package ir.hsadehi.HomeServices.model.dtos;

import ir.hsadehi.HomeServices.model.enums.UserRole;
import lombok.Data;

@Data
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserRole role; // CUSTOMER, SPECIALIST, ADMIN
}
