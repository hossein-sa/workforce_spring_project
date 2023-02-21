package sa.aref.service.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sa.aref.entity.order.Order;
import sa.aref.repository.order.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    private OrderService orderService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        orderService = new OrderService(orderRepository);
    }

    @Test
    public void testGetAllOrders() {
        List<Order> orders = new ArrayList<>();
        Order order1 = new Order();
        order1.setId(1);
        orders.add(order1);
        Order order2 = new Order();
        order2.setId(2);
        orders.add(order2);
        when(orderRepository.findAll()).thenReturn(orders);
        List<Order> result = orderService.getAllOrders();
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
    }

    @Test
    public void testGetOrderById() {
        Order order = new Order();
        order.setId(1);
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));
        Optional<Order> result = orderService.getOrderById(1);
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
    }

    @Test
    public void testSaveOrder() {
        Order order = new Order();
        order.setId(1);
        when(orderRepository.save(order)).thenReturn(order);
        Order result = orderService.saveOrder(order);
        assertEquals(1, result.getId());
    }
}