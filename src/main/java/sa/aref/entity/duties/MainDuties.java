package sa.aref.entity.duties;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class MainDuties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "mainDuties")
    private Set<SubDuties> duties;
}
