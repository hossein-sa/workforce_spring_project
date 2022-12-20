package sa.aref.service.order;

import org.springframework.stereotype.Service;
import sa.aref.entity.order.Order;
import sa.aref.repository.order.OrderRepository;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
