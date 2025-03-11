package ir.hsadehi.HomeServices.repository;

import ir.hsadehi.HomeServices.model.Order;
import ir.hsadehi.HomeServices.model.Proposal;
import ir.hsadehi.HomeServices.model.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    List<Proposal> findByOrder_Id(Long orderId);
    List<Proposal> findBySpecialist_Id(Long specialistId);
    List<Proposal> findByOrder_IdAndOrder_Customer_Id(Long orderId, Long customerId);
    Optional<Proposal> findByOrderAndOrder_Status(Order order, OrderStatus status);

    Optional<Proposal> findByOrder(Order order);
    Optional<Proposal> findByOrderId(Long orderId);
    Optional<Proposal> findByOrderAndAcceptedTrue(Order order);

}

