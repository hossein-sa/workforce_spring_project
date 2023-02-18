package sa.aref.entity.accounts;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sa.aref.entity.duties.SubDuties;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter

public class ExpertAccount extends User {

    @Enumerated(EnumType.STRING)
    private StatusExpert status;
    private int doneTaskCount;
    private int stars;
    @ManyToMany(mappedBy = "expertAccounts")
    private Set<SubDuties> subDuties;

    public ExpertAccount(StatusExpert status, int doneTaskCount, int stars, Set<SubDuties> subDuties) {
        this.status = status;
        this.doneTaskCount = doneTaskCount;
        this.stars = stars;
        this.subDuties = subDuties;
    }

    @Builder
    public ExpertAccount(Integer id, String firstname, String lastname, String email, String password, LocalDateTime registerDateTime, StatusExpert status, int doneTaskCount, int stars, Set<SubDuties> subDuties) {
        super(id, firstname, lastname, email, password);
        this.status = status;
        this.doneTaskCount = doneTaskCount;
        this.stars = stars;
        this.subDuties = subDuties;
    }
}
