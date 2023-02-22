package sa.aref.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sa.aref.entity.accounts.ExpertAccount;
import sa.aref.entity.accounts.StatusExpert;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeStatusExpertDto {
    private Integer id;
    private StatusExpert status;

    public static ChangeStatusExpertDto fromEntity(ExpertAccount expertAccount){
        return new ChangeStatusExpertDto(
                expertAccount.getId(),
                expertAccount.getStatus()
        );
    }

}
