package sa.aref.entity.accounts;

import sa.aref.base.entity.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ExpertAccount extends BaseEntity<Long> {
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(unique = true, nullable = false)
    private String nationalCode;
    private String email;
    @Enumerated(EnumType.STRING)
    private StatusExpert status;
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime registerDate;
    private Long balance;

}
