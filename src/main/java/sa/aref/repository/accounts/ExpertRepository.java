package sa.aref.repository.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sa.aref.entity.accounts.ExpertAccount;

import java.util.Optional;

@Repository
public interface ExpertRepository extends JpaRepository<ExpertAccount, Integer> {
    @Modifying
    @Query("""
            update ExpertAccount e
            set e.password = :password
            where e.id = :id
            """)
    void changePassword(Integer id, String password);

    boolean existsByEmail(String email);

    Optional<ExpertAccount> findByEmail(String email);


}
