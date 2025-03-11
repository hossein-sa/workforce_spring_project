package ir.hsadehi.HomeServices.model.dtos;

import lombok.Data;

@Data
public class OrderDTO {
    private Long customerId;
    private Long subServiceId;
    private String description;
    private double suggestedPrice;
    private String address;
}
