package sa.aref.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sa.aref.entity.order.Order;

import java.util.List;

@Repository

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAll();
}
