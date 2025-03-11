package ir.hsadehi.HomeServices.service;

import ir.hsadehi.HomeServices.model.Order;
import ir.hsadehi.HomeServices.model.Proposal;
import ir.hsadehi.HomeServices.model.Specialist;
import ir.hsadehi.HomeServices.model.dtos.ProposalDTO;
import ir.hsadehi.HomeServices.model.enums.OrderStatus;
import ir.hsadehi.HomeServices.repository.OrderRepository;
import ir.hsadehi.HomeServices.repository.ProposalRepository;
import ir.hsadehi.HomeServices.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProposalService {
    private final ProposalRepository proposalRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    // ✅ Specialists submit a proposal
    public String submitProposal(String specialistEmail, Long orderId, double proposedPrice, int estimatedDuration) {
        Specialist specialist = (Specialist) userRepository.findByEmail(specialistEmail)
                .orElseThrow(() -> new RuntimeException("Specialist not found!"));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found!"));

        if (!specialist.getServices().contains(order.getSubService())) {
            throw new RuntimeException("You are not allowed to bid on this order!");
        }

        Proposal proposal = new Proposal(order, specialist, proposedPrice, estimatedDuration);
        proposalRepository.save(proposal);

        return "Proposal submitted successfully!";
    }

    // ✅ Customers view all proposals for their order
    public List<ProposalDTO> getProposalsForCustomer(Long customerId, Long orderId) {
        List<Proposal> proposals = proposalRepository.findByOrder_IdAndOrder_Customer_Id(orderId, customerId);
        return proposals.stream().map(p -> new ProposalDTO(
                p.getId(),
                p.getOrder().getId(),
                p.getSpecialist().getId(),
                p.getProposedPrice(),
                p.getEstimatedDuration(),
                p.getProposalDate()
        )).collect(Collectors.toList());
    }

    // ✅ Customer selects a proposal
    public String selectProposal(Long customerId, Long proposalId) {
        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new RuntimeException("Proposal not found!"));

        if (!proposal.getOrder().getCustomer().getId().equals(customerId)) {
            throw new RuntimeException("You are not allowed to select this proposal!");
        }

        proposal.getOrder().setStatus(OrderStatus.WAITING_FOR_SPECIALIST_ARRIVAL);
        orderRepository.save(proposal.getOrder());

        return "Proposal selected successfully! Order is now assigned to the specialist.";
    }


}
