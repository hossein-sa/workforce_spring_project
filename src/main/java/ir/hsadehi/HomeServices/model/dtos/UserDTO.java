package ir.hsadehi.HomeServices.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}
