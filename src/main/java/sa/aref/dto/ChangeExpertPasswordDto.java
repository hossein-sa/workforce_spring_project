package sa.aref.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeExpertPasswordDto {
    private int expertId;
    private String currentPassword;
    private String newPassword;
}
