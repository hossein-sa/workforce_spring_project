package ir.hsadehi.HomeServices.controller;

import ir.hsadehi.HomeServices.model.dtos.LoginRequest;
import ir.hsadehi.HomeServices.model.dtos.RegistrationRequest;
import ir.hsadehi.HomeServices.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // **Register Endpoint**
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        String result = userService.registerUser(registrationRequest);
        return ResponseEntity.ok(result);
    }

    // **Login Endpoint**
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        String result = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(result);
    }
}
