package sa.aref.entity.order;

import jakarta.persistence.*;
import lombok.*;
import sa.aref.entity.accounts.ClientAccount;
import sa.aref.entity.duties.SubDuties;
import sa.aref.entity.offer.Offer;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "orders")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime dutyStartTime;
    private LocalDateTime dutyEndTime;
    private LocalDateTime dutyRequestTime;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientAccount clientAccount;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;
    private String address;
    private String description;
    @ManyToOne
    private SubDuties subDuties;
    private Integer rate;
    private String comment;
    @OneToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Offer> offers;
    @OneToOne
    @JoinColumn(name = "selected_offer_id")
    private Offer selectedOffer;

}