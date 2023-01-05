package sa.aref.entity.accounts;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import sa.aref.entity.duties.SubDuties;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ExpertAccount extends User {

    @Enumerated(EnumType.STRING)
    private StatusExpert status;
    private int doneTaskCount;
    private int stars;
    @ManyToMany(mappedBy = "expertAccounts")
    private Set<SubDuties> subDuties;
}
