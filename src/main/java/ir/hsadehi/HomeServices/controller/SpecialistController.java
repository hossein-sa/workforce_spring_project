package ir.hsadehi.HomeServices.controller;

import ir.hsadehi.HomeServices.service.SpecialistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specialist")
public class SpecialistController {
    private final SpecialistService specialistService;

    public SpecialistController(SpecialistService specialistService) {
        this.specialistService = specialistService;
    }

    // ✅ Specialists choose their expertise
    @PostMapping("/{id}/add-services")
    public ResponseEntity<String> addServices(
            @PathVariable Long id,
            @RequestBody List<Long> subServiceIds) {
        String response = specialistService.addSpecialistServices(id, subServiceIds);
        return ResponseEntity.ok(response);
    }
}
