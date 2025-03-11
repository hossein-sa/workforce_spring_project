package ir.hsadehi.HomeServices.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("ADMIN") // ✅ This ensures the role is stored as ADMIN
@Getter
@Setter
@NoArgsConstructor
public class Admin extends User {
}

