package ir.hsadehi.HomeServices.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private double amount;
    private boolean isOnlinePayment;

    public Payment(Order order, Customer customer, double amount, boolean isOnlinePayment) {
        this.order = order;
        this.customer = customer;
        this.amount = amount;
        this.isOnlinePayment = isOnlinePayment;
    }
}
