package ir.hsadehi.HomeServices.service;

import ir.hsadehi.HomeServices.model.MainService;
import ir.hsadehi.HomeServices.model.SubService;
import ir.hsadehi.HomeServices.model.dtos.CreateSubServiceRequest;
import ir.hsadehi.HomeServices.repository.MainServiceRepository;
import ir.hsadehi.HomeServices.repository.SubServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubServiceService {
    private final SubServiceRepository subServiceRepository;
    private final MainServiceRepository mainServiceRepository;

    public String addSubService(CreateSubServiceRequest request) {
        MainService mainService = mainServiceRepository.findById(request.getMainServiceId())
                .orElseThrow(() -> new RuntimeException("Main Service Not Found"));

        Optional<SubService> existingSubService = subServiceRepository.findByName(request.getName());
        if (existingSubService.isPresent()) {
            return "Sub Service Already Exists";
        }

        SubService subService = new SubService();
        subService.setName(request.getName());
        subService.setDescription(request.getDescription());
        subService.setBasePrice(request.getBasePrice());
        subService.setMainService(mainService);

        subServiceRepository.save(subService);
        return "Sub-service added successfully!";
    }
}
