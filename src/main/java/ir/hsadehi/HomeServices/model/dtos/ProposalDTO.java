package ir.hsadehi.HomeServices.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
public class ProposalDTO {
    private Long proposalId;
    private Long orderId;
    private Long specialistId;
    private double proposedPrice;
    private int estimatedDuration;
    private LocalDateTime proposalDate;
}
