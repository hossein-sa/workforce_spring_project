package ir.hsadehi.HomeServices.controller;

import ir.hsadehi.HomeServices.model.MainService;
import ir.hsadehi.HomeServices.model.dtos.CreateMainServiceRequest;
import ir.hsadehi.HomeServices.model.dtos.CreateSubServiceRequest;
import ir.hsadehi.HomeServices.model.dtos.MainServiceDTO;
import ir.hsadehi.HomeServices.model.dtos.UserDTO;
import ir.hsadehi.HomeServices.service.MainServiceService;
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
}
