package sa.aref.controller.duties;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sa.aref.dto.SubDutyDto;
import sa.aref.entity.duties.MainDuties;
import sa.aref.entity.duties.SubDuties;
import sa.aref.service.duty.MainDutyService;
import sa.aref.service.duty.SubDutyService;

import java.util.Optional;

@RestController
@RequestMapping("/sub-duties")
public class SubDutiesController {
    private final SubDutyService subDutyService;
    private final MainDutyService mainDutyService;

    public SubDutiesController(SubDutyService subDutyService, MainDutyService mainDutyService) {
        this.subDutyService = subDutyService;
        this.mainDutyService = mainDutyService;
    }

    @PostMapping("/new")
    public ResponseEntity<String> addNewSubDuty(@RequestBody SubDutyDto subDutyDto) {
        Optional<MainDuties> mainDuties = mainDutyService.findById(subDutyDto.getMainDutyId());
        subDutyService.addSubDuty(SubDuties.builder()
                .name(subDutyDto.getName())
                .mainDuties(mainDuties.get())
                .price(subDutyDto.getPrice())
                .description(subDutyDto.getDescription())
                .build());
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/add/{subDutiesId}/expert/{expertId}")
    public ResponseEntity<SubDuties> addExpertToSubDuties(@PathVariable Integer subDutiesId, @PathVariable Integer expertId) {
        SubDuties subDuties = subDutyService.addExpertToSubDuties(subDutiesId, expertId);
        return new ResponseEntity<>(subDuties, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{subDutiesId}/expert/{expertId}")
    public ResponseEntity<SubDuties> removeExpertFromSubDuties(@PathVariable Integer subDutiesId, @PathVariable Integer expertId) {
        SubDuties subDuties = subDutyService.removeExpertOfSubDuties(subDutiesId, expertId);
        return ResponseEntity.ok(subDuties);
    }

    @PutMapping("/edit-price/{subDutiesId}/price")
    public ResponseEntity<SubDuties> editSubDutyPrice(@PathVariable Integer subDutiesId, @RequestParam Long newPrice) {
        SubDuties subDuties = subDutyService.editSubDutyPrice(subDutiesId, newPrice);
        return ResponseEntity.ok(subDuties);
    }

    @PutMapping("/edit-description/{subDutiesId}/description")
    public ResponseEntity<SubDuties> editSubDutyDescription(@PathVariable Integer subDutiesId, @RequestParam String newDescription) {
        SubDuties subDuties = subDutyService.editSubDutyDescription(subDutiesId, newDescription);
        return ResponseEntity.ok(subDuties);
    }


}


