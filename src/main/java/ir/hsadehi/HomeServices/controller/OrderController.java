package ir.hsadehi.HomeServices.controller;

import ir.hsadehi.HomeServices.model.dtos.OrderDTO;
import ir.hsadehi.HomeServices.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO orderDTO) {
        String response = orderService.createOrder(orderDTO);
        return ResponseEntity.ok(response);
    }
}
