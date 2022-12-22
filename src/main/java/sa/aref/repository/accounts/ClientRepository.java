package sa.aref.repository.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sa.aref.entity.accounts.ClientAccount;
import sa.aref.entity.order.Order;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientAccount, Long> {
    @Modifying
    @Query("""
            update ClientAccount c
            set c.password = :password
            where c.id = :id
            """)
    void changePassword(Long id, String password);




}
