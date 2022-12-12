package sa.aref.entity.duties;

import sa.aref.base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SubDuties extends BaseEntity<Long> {
    private String name;
    private Double price;
    private String description;
    @ManyToOne
    private MainDuties mainDuties;

}
