package ir.hsadehi.HomeServices.repository;

import ir.hsadehi.HomeServices.model.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    List<Proposal> findByOrder_Id(Long orderId);
    List<Proposal> findBySpecialist_Id(Long specialistId);
}

