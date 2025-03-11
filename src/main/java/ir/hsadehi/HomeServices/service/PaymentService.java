package ir.hsadehi.HomeServices.service;

import ir.hsadehi.HomeServices.model.*;
import ir.hsadehi.HomeServices.model.enums.OrderStatus;
import ir.hsadehi.HomeServices.repository.OrderRepository;
import ir.hsadehi.HomeServices.repository.PaymentRepository;
import ir.hsadehi.HomeServices.repository.ProposalRepository;
import ir.hsadehi.HomeServices.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PaymentService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final ProposalRepository proposalRepository;

    public PaymentService(OrderRepository orderRepository, UserRepository userRepository, PaymentRepository paymentRepository, ProposalRepository proposalRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
        this.proposalRepository = proposalRepository;
    }

    @Transactional
    public String processPayment(Long customerId, Long orderId, boolean isOnlinePayment) {
        // ✅ Fetch the order
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found!"));

        // ✅ Ensure the order belongs to the customer
        if (!order.getCustomer().getId().equals(customerId)) {
            throw new RuntimeException("This order does not belong to you!");
        }

        // ✅ Ensure the order is completed before payment
        if (order.getStatus() != OrderStatus.COMPLETED) {
            throw new RuntimeException("Order is not yet completed. Payment is not allowed.");
        }

        double amount = order.getSuggestedPrice();
        double specialistShare = amount * 0.7;

        // ✅ Fetch customer
        Customer customer = (Customer) userRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found!"));

        // ✅ Fetch assigned specialist from the selected proposal
        Proposal acceptedProposal = proposalRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("No assigned specialist found!"));
        Specialist specialist = acceptedProposal.getSpecialist();

        // ✅ Ensure customer has enough balance
        if (customer.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance! Please add funds.");
        }

        // ✅ Deduct from customer balance
        customer.setBalance(customer.getBalance() - amount);
        userRepository.save(customer);

        // ✅ Add 70% to specialist balance
        specialist.setBalance(specialist.getBalance() + specialistShare);
        userRepository.save(specialist);

        // ✅ Mark order as PAID
        order.setStatus(OrderStatus.PAID);
        orderRepository.save(order);

        // ✅ Save payment record
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setCustomer(customer);
        payment.setSpecialist(specialist);
        payment.setAmount(amount);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setOnlinePayment(isOnlinePayment);
        paymentRepository.save(payment);

        return "Payment successful! The order is now marked as PAID.";
    }

}
