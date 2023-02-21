package sa.aref.entity.accounts;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",
            message = "password must contain at least  1 uppercase, 1 lowercase and 1 digit")
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime registerDateTime;
    private Long balance;
//    @Column(name = "verification_token")
//    private String emailVerificationToken;
    // other fields and mappings...

    public User() {
    }

    public User(Integer id, String firstname, String lastName, String email, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.registerDateTime = LocalDateTime.now();
        this.balance = 0L;
    }
}
