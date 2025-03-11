package ir.hsadehi.HomeServices.controller;

import ir.hsadehi.HomeServices.model.dtos.CompleteProfileRequest;
import ir.hsadehi.HomeServices.model.dtos.OrderResponseDTO;
import ir.hsadehi.HomeServices.model.dtos.ReviewDTO;
import ir.hsadehi.HomeServices.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specialist")
public class SpecialistController {
    private final SpecialistService specialistService;
    private final UserService userService;
    private final OrderService orderService;
    private final ProposalService proposalService;
    private final ReviewService reviewService;

    public SpecialistController(SpecialistService specialistService, UserService userService, OrderService orderService, ProposalService proposalService, ReviewService reviewService) {
        this.specialistService = specialistService;
        this.userService = userService;
        this.orderService = orderService;
        this.proposalService = proposalService;
        this.reviewService = reviewService;
    }

    // ✅ Specialists choose their expertise
    @PostMapping("/{id}/add-services")
    public ResponseEntity<String> addServices(
            @PathVariable Long id,
            @RequestBody List<Long> subServiceIds) {
        String response = specialistService.addSpecialistServices(id, subServiceIds);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/complete-profile")
    public ResponseEntity<String> completeProfile(
            Authentication authentication,
            @RequestBody CompleteProfileRequest request) {
        String email = authentication.getName();
        String response = userService.completeSpecialistProfile(email, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponseDTO>> getAvailableOrders(Authentication authentication) {
        String specialistEmail = authentication.getName(); // Get logged-in specialist
        List<OrderResponseDTO> orders = orderService.getAvailableOrdersForSpecialist(specialistEmail);
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/proposals")
    public ResponseEntity<String> submitProposal(
            Authentication authentication,
            @RequestParam Long orderId,
            @RequestParam double proposedPrice,
            @RequestParam int estimatedDuration) {
        String specialistEmail = authentication.getName();
        String response = proposalService.submitProposal(specialistEmail, orderId, proposedPrice, estimatedDuration);
        return ResponseEntity.ok(response);
    }

    // ✅ Specialists view their received reviews
    @GetMapping("/{specialistId}/reviews")
    public ResponseEntity<List<ReviewDTO>> getSpecialistReviews(@PathVariable Long specialistId) {
        List<ReviewDTO> reviews = reviewService.getReviewsForSpecialist(specialistId);
        return ResponseEntity.ok(reviews);
    }
}
