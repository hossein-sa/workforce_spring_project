package sa.aref.controller.duties;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import sa.aref.entity.duties.MainDuties;
import sa.aref.service.duty.MainDutyService;

import java.util.List;

@RestController
@RequestMapping("/main-duties")
public class MainDutiesController {
    private final MainDutyService mainDutyService;

    public MainDutiesController(MainDutyService mainDutyService) {
        this.mainDutyService = mainDutyService;
    }

    @GetMapping("/all")
    public List<MainDuties> getMainDuties() {
        return mainDutyService.getMainDuties();
    }

    @PostMapping("/new/{dutyName}")
    public String addNewMainDuty(@PathVariable String dutyName) {
        if (mainDutyService.addMainDuty(MainDuties.builder().name(dutyName).build()))
            return "OK";
        else
            return "Failed";
    }


}
