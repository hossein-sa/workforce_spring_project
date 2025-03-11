package ir.hsadehi.HomeServices.service;

import ir.hsadehi.HomeServices.model.Specialist;
import ir.hsadehi.HomeServices.model.SubService;
import ir.hsadehi.HomeServices.repository.SpecialistRepository;
import ir.hsadehi.HomeServices.repository.SubServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialistService {
    private final SpecialistRepository specialistRepository;
    private final SubServiceRepository subServiceRepository;

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
}
