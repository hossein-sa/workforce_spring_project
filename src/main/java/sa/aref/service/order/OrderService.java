package sa.aref.service.order;

import org.springframework.stereotype.Service;
import sa.aref.entity.order.Order;
import sa.aref.exception.CustomExceptionNotFound;
import sa.aref.repository.order.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(int id) {
        return orderRepository.findById(id);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
    public Order updateOrder(Order order) {
        Optional<Order> existingOrder = orderRepository.findById(order.getId());
        if (existingOrder.isPresent()) {
            Order updatedOrder = existingOrder.get();
            updatedOrder.setDutyStartTime(order.getDutyStartTime());
            updatedOrder.setDutyEndTime(order.getDutyEndTime());
            updatedOrder.setClientAccount(order.getClientAccount());
            updatedOrder.setOrderStatus(order.getOrderStatus());
            updatedOrder.setAddress(order.getAddress());
            updatedOrder.setDescription(order.getDescription());
            updatedOrder.setSubDuties(order.getSubDuties());
            updatedOrder.setRate(order.getRate());
            updatedOrder.setComment(order.getComment());
            updatedOrder.setOffers(order.getOffers());
            updatedOrder.setSelectedOffer(order.getSelectedOffer());
            return orderRepository.save(updatedOrder);
        } else {
            throw new CustomExceptionNotFound("Order not found with id " + order.getId());
        }
    }

    public void deleteOrderById(int id) {
        orderRepository.deleteById(id);
    }


}
