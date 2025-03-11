package ir.hsadehi.HomeServices.model.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CompleteProfileRequest {
    private String profilePictureUrl;
    private Set<Long> subServiceIds;
}
