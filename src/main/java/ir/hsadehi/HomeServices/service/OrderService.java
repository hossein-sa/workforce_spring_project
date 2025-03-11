package ir.hsadehi.HomeServices.service;

import ir.hsadehi.HomeServices.model.*;
import ir.hsadehi.HomeServices.model.dtos.OrderResponseDTO;
import ir.hsadehi.HomeServices.model.dtos.PlaceOrderRequest;
import ir.hsadehi.HomeServices.model.enums.OrderStatus;
import ir.hsadehi.HomeServices.model.enums.SpecialistStatus;
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
        Specialist specialist = (Specialist) userRepository.findByEmail(specialistEmail)
                .orElseThrow(() -> new RuntimeException("Specialist not found!"));

        // ✅ Block specialists with status PENDING_APPROVAL
        if (specialist.getStatus() == SpecialistStatus.PENDING_APPROVAL) {
            throw new RuntimeException("Your account is under review. You cannot view orders.");
        }

        List<SubService> specialistServices = new ArrayList<>(specialist.getServices());
        List<Order> availableOrders = orderRepository.findBySubServiceInAndStatus(specialistServices, OrderStatus.WAITING_FOR_PROPOSALS);

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

    public String completeOrder(Long specialistId, Long orderId) {
        // Find the order
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found!"));

        // Ensure the specialist assigned to this order is the one completing it
        Proposal acceptedProposal = proposalRepository.findByOrder(order)
                .orElseThrow(() -> new RuntimeException("No accepted proposal for this order!"));

        if (!acceptedProposal.getSpecialist().getId().equals(specialistId)) {
            throw new RuntimeException("You are not assigned to this order!");
        }

        // Ensure the order is in the correct state
        if (order.getStatus() != OrderStatus.STARTED) {
            throw new RuntimeException("Order is not in progress and cannot be completed!");
        }

        // Mark order as completed
        order.setStatus(OrderStatus.COMPLETED);
        orderRepository.save(order);

        return "Order has been marked as completed!";
    }






}
