package sa.aref.controller.duties;

import org.springframework.web.bind.annotation.*;
import sa.aref.entity.duties.MainDuties;
import sa.aref.service.duty.MainDutyService;

import java.util.List;
import java.util.Optional;

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
        if (mainDutyService.findByName(dutyName).isPresent())
            return "Failed";
        mainDutyService.addMainDuty(MainDuties.builder().name(dutyName).build());
        return "OK";
    }


    @GetMapping("/findById/{id}")
    public Optional<MainDuties> findMainDutyById(@PathVariable("id") int id) {
        return mainDutyService.findById(id);
    }

    @GetMapping("/findByName/{name}")
    public Optional<MainDuties> findMainDutyByName(@PathVariable String name) {
        return mainDutyService.findByName(name);
    }
}
