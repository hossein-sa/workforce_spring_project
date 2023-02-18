package sa.aref.dto;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class  SubDutyDto{
    String name;
    int mainDutyId;
    long price;
    String description;

}

