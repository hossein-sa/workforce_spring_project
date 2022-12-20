package sa.aref.repository.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sa.aref.entity.accounts.ClientAccount;
@Repository
public interface ClientRepository extends JpaRepository<ClientAccount,Long> {

}
