package sa.aref.repository.duty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sa.aref.entity.duties.MainDuty;
@Repository
public interface MainDutyRepository extends JpaRepository<MainDuty,Integer> {
}
