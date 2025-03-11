package ir.hsadehi.HomeServices.service;

import ir.hsadehi.HomeServices.model.Customer;
import ir.hsadehi.HomeServices.model.Order;
import ir.hsadehi.HomeServices.model.SubService;
import ir.hsadehi.HomeServices.model.dtos.OrderDTO;
import ir.hsadehi.HomeServices.repository.OrderRepository;
import ir.hsadehi.HomeServices.repository.SubServiceRepository;
import ir.hsadehi.HomeServices.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final SubServiceRepository subServiceRepository;

    public String createOrder(OrderDTO orderDTO) {
        // Find Customer
        Customer customer = userRepository.findById(orderDTO.getCustomerId())
                .filter(user -> user instanceof Customer)
                .map(user -> (Customer) user)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Find SubService
        SubService subService = subServiceRepository.findById(orderDTO.getSubServiceId())
                .orElseThrow(() -> new RuntimeException("SubService not found"));

        // Create Order
        Order order = new Order(
                customer,
                subService,
                orderDTO.getDescription(),
                orderDTO.getSuggestedPrice(),
                orderDTO.getAddress()
        );

        orderRepository.save(order);
        return "Order created successfully";
    }
}
