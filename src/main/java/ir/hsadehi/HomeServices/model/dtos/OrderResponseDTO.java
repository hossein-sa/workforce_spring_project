package ir.hsadehi.HomeServices.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponseDTO {
    private Long orderId;
    private Long customerId;
    private Long subServiceId;
    private String description;
    private double suggestedPrice;
    private String address;
}
