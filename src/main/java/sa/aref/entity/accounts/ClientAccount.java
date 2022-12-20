package sa.aref.entity.accounts;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ClientAccount{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    private Long balance;
}
