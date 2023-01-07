package sa.aref.entity.duties;

import jakarta.persistence.*;
import lombok.*;
import sa.aref.entity.accounts.ExpertAccount;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubDuties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String name;
    private Long price;
    private String description;
    @ManyToOne
    private MainDuties mainDuties;


    @ManyToMany
    private Set<ExpertAccount> expertAccounts;
}
