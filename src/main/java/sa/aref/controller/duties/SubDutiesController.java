package sa.aref.controller.duties;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public String addNewSubDuty(@RequestBody SubDutyDto subDutyDto) {
        Optional<MainDuties> mainDuties = mainDutyService.findById(subDutyDto.getMainDutyId());
        subDutyService.addSubDuty(SubDuties.builder()
                .name(subDutyDto.getName())
                .mainDuties(mainDuties.get())
                .price(subDutyDto.getPrice())
                .description(subDutyDto.getDescription())
                .build());
        return "OK";
    }

}


