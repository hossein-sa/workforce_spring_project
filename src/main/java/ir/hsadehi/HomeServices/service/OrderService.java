package ir.hsadehi.HomeServices.service;

import ir.hsadehi.HomeServices.model.*;
import ir.hsadehi.HomeServices.model.dtos.OrderResponseDTO;
import ir.hsadehi.HomeServices.model.dtos.PlaceOrderRequest;
import ir.hsadehi.HomeServices.model.enums.OrderStatus;
import ir.hsadehi.HomeServices.repository.OrderRepository;
import ir.hsadehi.HomeServices.repository.ProposalRepository;
import ir.hsadehi.HomeServices.repository.SubServiceRepository;
import ir.hsadehi.HomeServices.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final SubServiceRepository subServiceRepository;
    private final ProposalRepository proposalRepository;

    // ✅ Place an order
    public String placeOrder(String customerEmail, PlaceOrderRequest request) {
        // 🔹 Find the customer
        Customer customer = (Customer) userRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new RuntimeException("Customer not found!"));

        // 🔹 Find the requested sub-service
        SubService subService = subServiceRepository.findById(request.getSubServiceId())
                .orElseThrow(() -> new RuntimeException("Sub-service not found!"));

        // 🔹 Create the order
        Order order = new Order(
                customer,
                subService,
                request.getDescription(),
                request.getSuggestedPrice(),
                request.getAddress()
        );

        orderRepository.save(order);
        return "Order placed successfully!";
    }

    public List<OrderResponseDTO> getAvailableOrdersForSpecialist(String specialistEmail) {
        // 🔹 Find the specialist
        Specialist specialist = (Specialist) userRepository.findByEmail(specialistEmail)
                .orElseThrow(() -> new RuntimeException("Specialist not found!"));

        // ✅ Convert Set<SubService> to List<SubService>
        List<SubService> specialistServices = new ArrayList<>(specialist.getServices());

        // 🔹 Find available orders that match the specialist's sub-services
        List<Order> availableOrders = orderRepository.findBySubServiceInAndStatus(
                specialistServices, OrderStatus.WAITING_FOR_PROPOSALS);

        // 🔹 Convert to DTOs
        return availableOrders.stream()
                .map(order -> new OrderResponseDTO(
                        order.getId(),
                        order.getCustomer().getId(),
                        order.getSubService().getId(),
                        order.getDescription(),
                        order.getSuggestedPrice(),
                        order.getAddress()
                ))
                .toList();
    }


    public String startOrder(Long specialistId, Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found!"));

        // Ensure only the assigned specialist can start the order
        Proposal acceptedProposal = proposalRepository.findByOrderAndOrder_Status(order, OrderStatus.WAITING_FOR_SPECIALIST_ARRIVAL)
                .orElseThrow(() -> new RuntimeException("No accepted proposal found for this order!"));

        if (!acceptedProposal.getSpecialist().getId().equals(specialistId)) {
            throw new RuntimeException("You are not assigned to this order!");
        }

        // Ensure the order is in the correct state
        if (order.getStatus() != OrderStatus.WAITING_FOR_SPECIALIST_ARRIVAL) {
            throw new RuntimeException("Order is not ready to be started!");
        }

        // Update status to STARTED
        order.setStatus(OrderStatus.STARTED);
        orderRepository.save(order);

        return "Order has been started successfully!";
    }





}
