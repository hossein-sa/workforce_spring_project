package ir.hsadehi.HomeServices.controller;

import ir.hsadehi.HomeServices.model.MainService;
import ir.hsadehi.HomeServices.model.dtos.*;
import ir.hsadehi.HomeServices.service.MainServiceService;
import ir.hsadehi.HomeServices.service.SpecialistService;
import ir.hsadehi.HomeServices.service.SubServiceService;
import ir.hsadehi.HomeServices.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final MainServiceService mainServiceService;
    private final SubServiceService subServiceService;
    private final UserService userService;
    private final SpecialistService specialistService;

    // ** Add MainService (Admin Only)
    @PostMapping("/main-service")
    public ResponseEntity<String> addMainService(@RequestBody CreateMainServiceRequest request) {
        String response = mainServiceService.addMainService(request);
        return ResponseEntity.ok(response);
    }

    // **Add SubService (Admin Only)**
    @PostMapping("/sub-service")
    public ResponseEntity<String> addSubService(@RequestBody CreateSubServiceRequest request) {
        String response = subServiceService.addSubService(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/main-services")
    public ResponseEntity<List<MainServiceDTO>> getAllMainServices() {
        List<MainServiceDTO> services = mainServiceService.getAllMainServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/specialist/assign")
    public ResponseEntity<String> assignSpecialistToSubService(@RequestBody AssignSpecialistRequest request) {
        String response = specialistService.assignSpecialistToSubService(request.getSpecialistId(), request.getSubServiceId());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/specialist/remove")
    public ResponseEntity<String> removeSpecialistFromSubService(@RequestBody AssignSpecialistRequest request) {
        String response = specialistService.removeSpecialistFromSubService(request.getSpecialistId(), request.getSubServiceId());
        return ResponseEntity.ok(response);
    }
}
