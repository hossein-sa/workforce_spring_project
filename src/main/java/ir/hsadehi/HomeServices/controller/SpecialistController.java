package ir.hsadehi.HomeServices.controller;

import ir.hsadehi.HomeServices.model.dtos.CompleteProfileRequest;
import ir.hsadehi.HomeServices.service.SpecialistService;
import ir.hsadehi.HomeServices.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specialist")
public class SpecialistController {
    private final SpecialistService specialistService;
    private final UserService userService;

    public SpecialistController(SpecialistService specialistService, UserService userService) {
        this.specialistService = specialistService;
        this.userService = userService;
    }

    // ✅ Specialists choose their expertise
    @PostMapping("/{id}/add-services")
    public ResponseEntity<String> addServices(
            @PathVariable Long id,
            @RequestBody List<Long> subServiceIds) {
        String response = specialistService.addSpecialistServices(id, subServiceIds);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/complete-profile")
    public ResponseEntity<String> completeProfile(
            Authentication authentication,
            @RequestBody CompleteProfileRequest request) {
        String email = authentication.getName();
        String response = userService.completeSpecialistProfile(email, request);
        return ResponseEntity.ok(response);
    }
}
