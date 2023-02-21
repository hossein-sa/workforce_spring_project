package sa.aref.entity.verification;

import jakarta.persistence.*;
import lombok.*;
import sa.aref.entity.accounts.ClientAccount;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(targetEntity = ClientAccount.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private ClientAccount clientAccount;

    private LocalDateTime expiryDate;
}
