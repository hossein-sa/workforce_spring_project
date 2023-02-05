package sa.aref.controller.duties;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sa.aref.entity.duties.MainDuties;
import sa.aref.entity.duties.SubDuties;
import sa.aref.service.duty.SubDutyService;

@RestController
@RequestMapping("/sub-duties")
public class SubDutiesController {
    private final SubDutyService subDutyService;

    public SubDutiesController(SubDutyService subDutyService) {
        this.subDutyService = subDutyService;
    }

    @PostMapping("/new")
    public String addSubDuty() {
        return "OK";
    }
}
