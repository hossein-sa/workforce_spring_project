package sa.aref.repository.duty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sa.aref.entity.duties.SubDuties;

@Repository
public interface SubDutyRepository extends JpaRepository<SubDuties, Long> {
}
