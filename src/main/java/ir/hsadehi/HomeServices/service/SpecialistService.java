package ir.hsadehi.HomeServices.service;

import ir.hsadehi.HomeServices.model.Specialist;
import ir.hsadehi.HomeServices.model.SubService;
import ir.hsadehi.HomeServices.model.enums.SpecialistStatus;
import ir.hsadehi.HomeServices.repository.ReviewRepository;
import ir.hsadehi.HomeServices.repository.SpecialistRepository;
import ir.hsadehi.HomeServices.repository.SubServiceRepository;
import ir.hsadehi.HomeServices.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialistService {
    private final SpecialistRepository specialistRepository;
    private final SubServiceRepository subServiceRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    public String addSpecialistServices(Long specialistId, List<Long> subServiceIds) {
        Specialist specialist = specialistRepository.findById(specialistId).orElseThrow(() -> new RuntimeException("Specialist not found"));

        List<SubService> subServices = subServiceRepository.findAllById(subServiceIds);

        if (subServices.isEmpty()) {
            return "No valid sub-services found";
        }

        specialist.getServices().addAll(subServices);
        specialistRepository.save(specialist);

        return "Services added to specialist successfully";
    }

    @Transactional
    public String assignSpecialistToSubService(Long specialistId, Long subServiceId) {
        Specialist specialist = specialistRepository.findById(specialistId).orElseThrow(() -> new RuntimeException("Specialist not found"));


        if (specialist.getStatus() != SpecialistStatus.APPROVED) {
            return "Specialist must be approved before being assigned a service.";
        }

        SubService subService = subServiceRepository.findById(subServiceId).orElseThrow(() -> new RuntimeException("SubService not found"));


        if (specialist.getServices().contains(subService)) {
            return "Specialist is already assigned to this service";
        }

        specialist.getServices().add(subService);
        specialistRepository.save(specialist);
        return "Specialist assigned to the sub-service successfully";
    }

    @Transactional
    public String removeSpecialistFromSubService(Long specialistId, Long subServiceId) {
        Specialist specialist = specialistRepository.findById(specialistId).orElseThrow(() -> new RuntimeException("Specialist not found"));

        SubService subService = subServiceRepository.findById(subServiceId).orElseThrow(() -> new RuntimeException("Sub-service not found"));

        if (!specialist.getServices().contains(subService)) {
            return "Specialist is not assigned to this service";
        }

        specialist.getServices().remove(subService);
        specialistRepository.save(specialist);
        return "Specialist removed from the sub-service successfully";
    }

    @Transactional
    public String updateSpecialistStatus(Long specialistId, SpecialistStatus status) {
        Specialist specialist = specialistRepository.findById(specialistId).orElseThrow(() -> new RuntimeException("Specialist not found"));

        specialist.setStatus(status);
        specialistRepository.save(specialist);

        return "Specialist status updated to: " + status;
    }

    public void updateSpecialistRating(Long specialistId) {
        Specialist specialist = (Specialist) userRepository.findById(specialistId)
                .orElseThrow(() -> new RuntimeException("Specialist not found!"));

        // ✅ Calculate the average rating from reviews
        double averageRating = reviewRepository.calculateAverageRatingForSpecialist(specialistId)
                .orElse(0.0); // Default to 0 if no reviews exist

        specialist.setRating(averageRating);

        // ✅ If the rating drops below 3.5, change status to PENDING_APPROVAL
        if (averageRating < 3.5) {
            specialist.setStatus(SpecialistStatus.PENDING_APPROVAL);
        }

        userRepository.save(specialist);
    }

}
