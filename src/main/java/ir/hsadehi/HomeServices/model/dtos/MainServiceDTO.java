package ir.hsadehi.HomeServices.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MainServiceDTO {
    private Long id;
    private String name;
    private List<SubServiceDTO> subServices;
}
