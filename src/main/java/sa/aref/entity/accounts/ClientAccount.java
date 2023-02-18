package sa.aref.entity.accounts;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ClientAccount extends User {
    @Builder
    public ClientAccount(Integer id, String firstName, String lastName, String email, String password, LocalDateTime registerDateTime) {
        super(id, firstName, lastName, email, password);
    }


}
