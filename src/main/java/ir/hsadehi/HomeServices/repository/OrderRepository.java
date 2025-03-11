package ir.hsadehi.HomeServices.repository;

import ir.hsadehi.HomeServices.model.Order;
import ir.hsadehi.HomeServices.model.SubService;
import ir.hsadehi.HomeServices.model.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer_Id(Long customerId);
    List<Order> findByStatus(OrderStatus status);

    List<Order> findBySubServiceInAndStatus(List<SubService> subServices, OrderStatus status);

}