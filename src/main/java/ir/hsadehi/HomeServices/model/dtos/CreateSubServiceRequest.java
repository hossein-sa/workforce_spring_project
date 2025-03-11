package ir.hsadehi.HomeServices.model.dtos;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSubServiceRequest {
    private String name;
    private String description;
    private double basePrice;
    private Long mainServiceId; // ID of the parent MainService
}
