package ir.hsadehi.HomeServices.model;

import ir.hsadehi.HomeServices.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "sub_service_id", nullable = false)
    private SubService subService;

    private String description;
    private double suggestedPrice;
    private LocalDateTime orderDate;
    private String address;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order(Customer customer, SubService subService, String description, double suggestedPrice, String address) {
        this.customer = customer;
        this.subService = subService;
        this.description = description;
        this.suggestedPrice = suggestedPrice;
        this.address = address;
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.WAITING_FOR_PROPOSALS;
    }
}



