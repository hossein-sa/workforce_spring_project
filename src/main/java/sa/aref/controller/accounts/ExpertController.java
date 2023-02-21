package sa.aref.controller.accounts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sa.aref.dto.ChangeExpertPasswordDto;
import sa.aref.dto.ExpertDto;
import sa.aref.entity.accounts.ExpertAccount;
import sa.aref.entity.accounts.StatusExpert;
import sa.aref.exception.CustomExceptionInvalid;
import sa.aref.exception.CustomExceptionIsExist;
import sa.aref.exception.CustomExceptionNotFound;
import sa.aref.service.accounts.ExpertService;

@RestController
@RequestMapping("/expert")
public class ExpertController {
    private final ExpertService expertService;

    public ExpertController(ExpertService expertService) {
        this.expertService = expertService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody ExpertDto expertDto) {
        try {
            expertService.register(ExpertAccount.builder()
                    .firstname(expertDto.getFirstName())
                    .lastname(expertDto.getLastName())
                    .email(expertDto.getEmail())
                    .password(expertDto.getPassword())
                    .status(StatusExpert.PENDING) // default status for new experts
                    .doneTaskCount(0) // default done task count for new experts
                    .stars(0) // default stars for new experts
                    .build());
            return ResponseEntity.ok("Registration completed successfully");
        } catch (CustomExceptionIsExist e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangeExpertPasswordDto changeExpertPasswordDto) {
        try {
            expertService.changePassword(changeExpertPasswordDto.getExpertId(), changeExpertPasswordDto.getCurrentPassword(), changeExpertPasswordDto.getNewPassword());
            return ResponseEntity.ok("Password changed successfully");
        } catch (CustomExceptionNotFound | CustomExceptionInvalid e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
