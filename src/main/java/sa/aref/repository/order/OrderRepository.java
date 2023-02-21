package sa.aref.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sa.aref.entity.order.Order;
import sa.aref.entity.order.OrderStatus;

import java.util.List;

@Repository

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAll();

    // Method for changing order status
    @Modifying
    @Query("UPDATE Order o SET o.orderStatus = :orderStatus WHERE o.id = :orderId")
    void updateOrderStatus(@Param("orderId") Integer orderId, @Param("orderStatus") OrderStatus orderStatus);

}
