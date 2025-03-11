package ir.hsadehi.HomeServices.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "main_services") // Renamed table to main_services
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MainService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "mainService", cascade = CascadeType.ALL)
    private Set<SubService> subServices;
}

