package ir.hsadehi.HomeServices.service;

import ir.hsadehi.HomeServices.model.Admin;
import ir.hsadehi.HomeServices.model.Customer;
import ir.hsadehi.HomeServices.model.dtos.ChangePasswordRequest;
import ir.hsadehi.HomeServices.model.dtos.RegistrationRequest;
import ir.hsadehi.HomeServices.model.Specialist;
import ir.hsadehi.HomeServices.model.User;
import ir.hsadehi.HomeServices.model.dtos.UserDTO;
import ir.hsadehi.HomeServices.model.enums.UserRole;
import ir.hsadehi.HomeServices.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ Automatically create an admin if not exists
    @PostConstruct
    public void createDefaultAdmin() {
        Optional<User> admin = userRepository.findByEmail("admin@example.com");
        if (admin.isEmpty()) {
            // ✅ Use the Admin entity (NOT Customer)
            Admin adminUser = new Admin();
            adminUser.setFirstName("Super");
            adminUser.setLastName("Admin");
            adminUser.setEmail("admin@example.com");
            adminUser.setPassword(passwordEncoder.encode("admin123")); // Secure password
            adminUser.setRole(UserRole.ADMIN.name()); // ✅ Convert enum to string
            adminUser.setVerified(true);

            userRepository.save(adminUser);
            System.out.println("✅ Default admin created: admin@example.com / admin123");
        }
    }

    // **Register a new user**
    public String registerUser(RegistrationRequest registrationRequest) {
        Optional<User> existingUser = userRepository.findByEmail(registrationRequest.getEmail());
        if (existingUser.isPresent()) {
            return "User already exists";
        }

        User user;

        switch (registrationRequest.getRole()) {
            case CUSTOMER:
                Customer customer = new Customer();
                customer.setBalance(0.0);
                user = customer;
                break;
            case SPECIALIST:
                Specialist specialist = new Specialist();
                specialist.setRating(0.0);
                user = specialist;
                break;
            default:
                return "Invalid role!";
        }

        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setRegisteredAt(LocalDateTime.now());
        user.setVerified(false);

        userRepository.save(user);

        return "User registered successfully!";
    }

    // **Login user**
    public String loginUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return "User not found!";
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "Incorrect password!";
        }

        return "Login successful!";
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().toString()); // Convert enum to String
        return dto;
    }


    public String changePassword(String email, ChangePasswordRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        // check if old password is correct
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Old password is incorrect!");
        }

        // Hash the new password
        String hashedNewPassword = passwordEncoder.encode(request.getNewPassword());
        user.setPassword(hashedNewPassword);

        // save the updated password
        userRepository.save(user);
        return "Password changed successfully!";
    }

}
