package sa.aref.entity.accounts;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    @Column(unique = true, nullable = false)
    private String email;
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",
            message = "password must contain at least  1 uppercase, 1 lowercase and 1 digit")
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime registerDateTime;
    private Long balance;

}
