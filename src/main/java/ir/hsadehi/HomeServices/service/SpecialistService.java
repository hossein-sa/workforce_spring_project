package ir.hsadehi.HomeServices.service;

import ir.hsadehi.HomeServices.model.Specialist;
import ir.hsadehi.HomeServices.model.SubService;
import ir.hsadehi.HomeServices.repository.SpecialistRepository;
import ir.hsadehi.HomeServices.repository.SubServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialistService {
    private final SpecialistRepository specialistRepository;
    private final SubServiceRepository subServiceRepository;

    public String addSpecialistServices(Long specialistId, List<Long> subServiceIds) {
        Specialist specialist = specialistRepository.findById(specialistId)
                .orElseThrow(() -> new RuntimeException("Specialist not found"));

        List<SubService> subServices = subServiceRepository.findAllById(subServiceIds);

        if (subServices.isEmpty()) {
            return "No valid sub-services found";
        }

        specialist.getServices().addAll(subServices);
        specialistRepository.save(specialist);

        return "Services added to specialist successfully";
    }
}
