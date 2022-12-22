package sa.aref.repository.duty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sa.aref.entity.duties.MainDuties;
@Repository
public interface MainDutyRepository extends JpaRepository<MainDuties,Long> {
}
