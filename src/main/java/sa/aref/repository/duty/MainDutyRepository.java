package sa.aref.repository.duty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sa.aref.entity.duties.MainDuties;

import java.util.Optional;

@Repository
public interface MainDutyRepository extends JpaRepository<MainDuties, Integer> {
    Optional<MainDuties> findById(int id);

    Optional<MainDuties> findByName(String name);

    boolean existsByName(String name);

}
