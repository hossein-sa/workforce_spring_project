package ir.hsadehi.HomeServices.model;

import ir.hsadehi.HomeServices.model.enums.SpecialistStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("SPECIALIST")
@Getter
@Setter
@NoArgsConstructor
public class Specialist extends User {

    private double rating = 0.0;
    private String profilePictureUrl;

    @ManyToMany
    @JoinTable(
            name = "specialist_services",
            joinColumns = @JoinColumn(name = "specialist_id"),
            inverseJoinColumns = @JoinColumn(name = "subservice_id")
    )
    private Set<SubService> services = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private SpecialistStatus status = SpecialistStatus.NEW;
}

