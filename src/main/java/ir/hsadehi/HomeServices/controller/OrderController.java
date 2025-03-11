package ir.hsadehi.HomeServices.controller;

import ir.hsadehi.HomeServices.model.dtos.PlaceOrderRequest;
import ir.hsadehi.HomeServices.model.dtos.ProposalDTO;
import ir.hsadehi.HomeServices.service.OrderService;
import ir.hsadehi.HomeServices.service.ProposalService;
import ir.hsadehi.HomeServices.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ProposalService proposalService;
    private final UserService userService;


    @PostMapping("/create")
    public ResponseEntity<String> placeOrder(
            Authentication authentication, // To get the logged-in customer
            @RequestBody PlaceOrderRequest request) {
        String customerEmail = authentication.getName(); // Get logged-in customer email
        String response = orderService.placeOrder(customerEmail, request);
        return ResponseEntity.ok(response);
    }

    // ✅ View all proposals for an order
    @GetMapping("/{customerId}/orders/{orderId}/proposals")
    public ResponseEntity<List<ProposalDTO>> getProposals(@PathVariable Long customerId, @PathVariable Long orderId) {
        List<ProposalDTO> proposals = proposalService.getProposalsForCustomer(customerId, orderId);
        return ResponseEntity.ok(proposals);
    }

    // ✅ Select a proposal
    @PostMapping("/{customerId}/orders/proposals/{proposalId}/select")
    public ResponseEntity<String> selectProposal(@PathVariable Long customerId, @PathVariable Long proposalId) {
        String response = proposalService.selectProposal(customerId, proposalId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/orders/{orderId}/start")
    public ResponseEntity<String> startOrder(
            Authentication authentication,
            @PathVariable Long orderId) {
        String specialistEmail = authentication.getName();
        Long specialistId = userService.getUserIdByEmail(specialistEmail);

        String response = orderService.startOrder(specialistId, orderId);
        return ResponseEntity.ok(response);
    }


}
