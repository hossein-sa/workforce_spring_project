package sa.aref.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sa.aref.dto.ChangeStatusExpertDto;
import sa.aref.entity.accounts.ExpertAccount;
import sa.aref.entity.accounts.StatusExpert;
import sa.aref.exception.CustomExceptionNotFound;
import sa.aref.service.accounts.ExpertService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final ExpertService expertService;


    public AdminController(ExpertService expertService) {
        this.expertService = expertService;
    }

    @PostMapping("/experts/change-status")
    public ResponseEntity<String> changeExpertStatus(@RequestBody ChangeStatusExpertDto changeStatusExpertDto) {
        expertService.changeExpertStatus(changeStatusExpertDto);
        return ResponseEntity.ok("ExpertAccount with id " + changeStatusExpertDto.getId() + " has been updated.");
    }

    @GetMapping("/experts/pending")
    public ResponseEntity<List<ChangeStatusExpertDto>> getPendingExperts() {
        List<ChangeStatusExpertDto> expertAccountDtos = expertService
                .findByStatus(StatusExpert.PENDING)
                .stream()
                .map(ChangeStatusExpertDto::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(expertAccountDtos);
    }


}
