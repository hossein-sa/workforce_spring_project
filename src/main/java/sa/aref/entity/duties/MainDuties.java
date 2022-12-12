package sa.aref.entity.duties;

import sa.aref.base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Set;
@Entity
public class MainDuties extends BaseEntity<Long> {
    private String name;
    @OneToMany(mappedBy = "mainDuties")
    private Set<SubDuties> duties;
}
