package ir.hsadehi.HomeServices.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubServiceDTO {
    private Long id;
    private String name;
    private String description;
    private double basePrice;
}

