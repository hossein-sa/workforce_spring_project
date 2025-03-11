package ir.hsadehi.HomeServices.service;

import ir.hsadehi.HomeServices.model.*;
import ir.hsadehi.HomeServices.model.dtos.ReviewDTO;
import ir.hsadehi.HomeServices.model.enums.OrderStatus;
import ir.hsadehi.HomeServices.repository.ProposalRepository;
import ir.hsadehi.HomeServices.repository.ReviewRepository;
import ir.hsadehi.HomeServices.repository.UserRepository;
import ir.hsadehi.HomeServices.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProposalRepository proposalRepository;
    private final SpecialistService specialistService;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, OrderRepository orderRepository, ProposalRepository proposalRepository, SpecialistService specialistService) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.proposalRepository = proposalRepository;
        this.specialistService = specialistService;
    }

    // ✅ Submit a Review
    public String submitReview(String customerEmail, long orderId, ReviewDTO reviewDTO) {
        Customer customer = (Customer) userRepository.findByEmail(customerEmail)
                .orElseThrow(() -> new RuntimeException("Customer not found!"));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found!"));

        if (!order.getCustomer().getId().equals(customer.getId())) {
            throw new RuntimeException("This order does not belong to you!");
        }

        if (!order.getStatus().equals(OrderStatus.COMPLETED)) {
            throw new RuntimeException("You can only review completed orders.");
        }

        // ✅ Retrieve the assigned specialist from the accepted proposal
        Proposal acceptedProposal = proposalRepository.findByOrderAndAcceptedTrue(order)
                .orElseThrow(() -> new RuntimeException("No accepted proposal found for this order!"));

        Specialist specialist = acceptedProposal.getSpecialist();

        // ✅ Save new review
        Review review = new Review(customer, specialist, reviewDTO.getRating(), reviewDTO.getComment());
        reviewRepository.save(review);

        // ✅ Recalculate specialist rating after review submission
        specialistService.updateSpecialistRating(specialist.getId());

        return "Review submitted successfully!";
    }



    // ✅ View Reviews for a Specialist
    public List<ReviewDTO> getReviewsForSpecialist(Long specialistId) {
        return reviewRepository.findBySpecialistId(specialistId)
                .stream()
                .map(review -> new ReviewDTO(
                        review.getCustomer().getId(),
                        review.getSpecialist().getId(),
                        review.getRating(),
                        review.getComment(),
                        review.getReviewDate()
                ))
                .collect(Collectors.toList());
    }
}
