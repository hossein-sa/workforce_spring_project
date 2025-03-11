package ir.hsadehi.HomeServices.model.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Long customerId;
    private Long specialistId;
    private int rating;
    private String comment;
    private LocalDateTime reviewDate;
}
