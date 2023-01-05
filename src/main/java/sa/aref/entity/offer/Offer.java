package sa.aref.entity.offer;

import jakarta.persistence.*;
import lombok.*;
import sa.aref.entity.accounts.ExpertAccount;
import sa.aref.entity.order.Order;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime requestOfferTime;
    private Long offerPrice;
    private int estimateTime;
    @ManyToOne
    private Order order;
    @OneToOne
    @JoinColumn(name = "expert_account_id")
    private ExpertAccount expertAccount;

}
