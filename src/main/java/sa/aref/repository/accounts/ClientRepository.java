package sa.aref.repository.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sa.aref.entity.accounts.ClientAccount;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientAccount, Long> {
    Optional<ClientAccount> findByEmail(String email);

    boolean existsByEmail(String email);


}
