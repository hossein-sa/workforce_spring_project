package sa.aref.repository.verification;

import org.springframework.data.jpa.repository.JpaRepository;
import sa.aref.entity.verification.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
}
