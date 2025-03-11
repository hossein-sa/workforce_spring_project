package ir.hsadehi.HomeServices.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignSpecialistRequest {
    private Long specialistId;
    private Long subServiceId;
}
