package sa.aref.controller;

import org.springframework.web.bind.annotation.*;
import sa.aref.entity.duties.MainDuty;
import sa.aref.service.duty.MainDutyService;

import java.util.List;

@RestController
@RequestMapping("/main-duty")
public class MainDutyController {
    private final MainDutyService mainDutyService;

    public MainDutyController(MainDutyService mainDutyService) {
        this.mainDutyService = mainDutyService;
    }

    @GetMapping("/all")
    public List<MainDuty> getMainDuties() {
        return mainDutyService.getMainDuties();
    }

    @PostMapping("/new/{dutyName}")
    public String addNewMainDuty(@PathVariable String dutyName) {
        if (mainDutyService.addMainDuty(MainDuty.builder().name(dutyName).build()))
            return "OK";
        else
            return "Failed";
    }


}
