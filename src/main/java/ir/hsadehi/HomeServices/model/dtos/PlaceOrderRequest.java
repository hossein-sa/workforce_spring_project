package ir.hsadehi.HomeServices.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderRequest {
    private Long subServiceId;
    private String description;
    private double suggestedPrice;
    private String address;
}
