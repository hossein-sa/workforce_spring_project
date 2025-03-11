package ir.hsadehi.HomeServices.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "proposals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Proposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "specialist_id", nullable = false)
    private Specialist specialist;

    private double proposedPrice;
    private LocalDateTime proposalDate;
    private int estimatedDuration; // in hours

    public Proposal(Order order, Specialist specialist, double proposedPrice, int estimatedDuration) {
        this.order = order;
        this.specialist = specialist;
        this.proposedPrice = proposedPrice;
        this.estimatedDuration = estimatedDuration;
        this.proposalDate = LocalDateTime.now();
    }
}
