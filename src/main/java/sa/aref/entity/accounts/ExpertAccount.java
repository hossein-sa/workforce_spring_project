package sa.aref.entity.accounts;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

@Entity
public class ExpertAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String nationalCode;
    private String email;
    @Enumerated(EnumType.STRING)
    private StatusExpert status;
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",
            message = "password must contain at least  1 uppercase, 1 lowercase and 1 digit")
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime registerDate;
    private Long balance;

}
