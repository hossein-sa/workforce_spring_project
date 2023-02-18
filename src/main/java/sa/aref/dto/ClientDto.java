package sa.aref.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClientDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
