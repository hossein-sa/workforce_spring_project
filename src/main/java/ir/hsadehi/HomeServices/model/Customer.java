package ir.hsadehi.HomeServices.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("CUSTOMER")
@Getter
@Setter
@NoArgsConstructor
public class Customer extends User {
    private double balance = 0.0;
}

