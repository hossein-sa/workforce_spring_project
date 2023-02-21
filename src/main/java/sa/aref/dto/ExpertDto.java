package sa.aref.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpertDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;


}
