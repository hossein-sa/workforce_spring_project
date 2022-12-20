package sa.aref.entity.order;

import sa.aref.base.entity.BaseEntity;
import sa.aref.entity.accounts.ClientAccount;
import sa.aref.entity.accounts.ExpertAccount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import sa.aref.entity.duties.SubDuties;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "order")

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Order extends BaseEntity<Long> {
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime orderTime;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientAccount client;

    @ManyToOne
    @JoinColumn(name = "selected_expert_id")
    private ExpertAccount selectedExpert;


    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "order_expert",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "expert_id"))
    private Set<ExpertAccount> expertAccounts = new LinkedHashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", length = 20)
    private OrderStatus orderStatus;

    private String address;
    private String description;
    @ManyToOne
    private SubDuties duty;

}