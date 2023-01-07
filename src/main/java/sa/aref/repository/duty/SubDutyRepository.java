package sa.aref.repository.duty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sa.aref.entity.duties.SubDuty;

import java.util.List;

@Repository
public interface SubDutyRepository extends JpaRepository<SubDuty, Long> {
    List<SubDuty> findByMainDutiesId(Long id);
@Modifying
@Query("""
    update SubDuty s
    set s.price = :price
    where s.id = :subDutyId
""")
    void changeDutyPrice(Long subDutyId,Double price);
}
