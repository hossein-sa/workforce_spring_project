package sa.aref.repository.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sa.aref.entity.accounts.ExpertAccount;

@Repository
public interface ExpertRepository extends JpaRepository<ExpertAccount, Long> {


}
