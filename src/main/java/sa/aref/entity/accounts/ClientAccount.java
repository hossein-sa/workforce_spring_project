package sa.aref.entity.accounts;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import sa.aref.base.entity.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ClientAccount extends BaseEntity<Long> {
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(unique = true, nullable = false)
    private String nationalCode;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime registerDate;
}
