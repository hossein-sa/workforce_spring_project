package sa.aref.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeClientPasswordDto {
    private long clientId;
    private String currentPassword;
    private String newPassword;
}
