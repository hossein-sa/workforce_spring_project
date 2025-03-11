package ir.hsadehi.HomeServices.repository;

import ir.hsadehi.HomeServices.model.Order;
import ir.hsadehi.HomeServices.model.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer_Id(Long customerId);
    List<Order> findByStatus(OrderStatus status);
}
