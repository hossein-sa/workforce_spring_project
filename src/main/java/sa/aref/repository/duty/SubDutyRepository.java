package sa.aref.repository.duty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sa.aref.entity.duties.SubDuties;

import java.util.Optional;

@Repository
public interface SubDutyRepository extends JpaRepository<SubDuties, Long> {
    Optional<SubDuties> findById(int id);

    boolean existsByName(String name);


//    List<SubDuties> findByMainDutiesId(Long id);
//@Modifying
//@Query("""
//    update SubDuties s
//    set s.price = :price

//    where s.id = :subDutyId
//""")
//    void changeDutyPrice(Long subDutyId,Double price);

}
