package sa.aref.repository.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sa.aref.entity.accounts.ExpertAccount;
import sa.aref.entity.order.Order;

import java.util.List;

@Repository
public interface ExpertRepository extends JpaRepository<ExpertAccount, Long> {
    @Modifying
    @Query("""
            update ExpertAccount e
            set e.password = :password
            where e.id = :id
            """)
    void changePassword(Long id, String password);
    @Query("""
select o from Order as o where o.expertAccounts = :expertId
""")
    List<Order> findByExpertId(Long expertId);
}
