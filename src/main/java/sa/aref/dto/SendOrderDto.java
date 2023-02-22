package sa.aref.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sa.aref.entity.order.Order;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendOrderDto {
    private LocalDateTime dutyStartTime;
    private LocalDateTime dutyEndTime;
    private String address;
    private String description;
    private int subDutyId;

    public static SendOrderDto fromEntity(Order order) {
        return SendOrderDto.builder()
                .dutyStartTime(order.getDutyStartTime())
                .dutyEndTime(order.getDutyEndTime())
                .address(order.getAddress())
                .description(order.getDescription())
                .subDutyId(order.getSubDuties().getId())
                .build();
    }


}
