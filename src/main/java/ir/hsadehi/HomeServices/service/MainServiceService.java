package ir.hsadehi.HomeServices.service;

import ir.hsadehi.HomeServices.model.MainService;
import ir.hsadehi.HomeServices.model.dtos.CreateMainServiceRequest;
import ir.hsadehi.HomeServices.model.dtos.MainServiceDTO;
import ir.hsadehi.HomeServices.model.dtos.SubServiceDTO;
import ir.hsadehi.HomeServices.repository.MainServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainServiceService {
    private final MainServiceRepository mainServiceRepository;

    public String addMainService(CreateMainServiceRequest request) {
        Optional<MainService> existingService = mainServiceRepository.findByName(request.getName());
        if (existingService.isPresent()) {
            return "Main Service already exists!";
        }

        MainService mainService = new MainService();
        mainService.setName(request.getName());
        mainServiceRepository.save(mainService);
        return "Main service added successfully!";
    }

    // ✅ Fix: Corrected return type to List<MainServiceDTO>
    public List<MainServiceDTO> getAllMainServices() {
        List<MainService> mainServices = mainServiceRepository.findAll();
        return mainServices.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // ✅ Fix: Used Collectors.toList() instead of toList()
    private MainServiceDTO convertToDTO(MainService mainService) {
        MainServiceDTO dto = new MainServiceDTO();
        dto.setId(mainService.getId());
        dto.setName(mainService.getName());

        List<SubServiceDTO> subServices = mainService.getSubServices()
                .stream()
                .map(subService -> {
                    SubServiceDTO subServiceDTO = new SubServiceDTO();
                    subServiceDTO.setId(subService.getId());
                    subServiceDTO.setName(subService.getName());
                    subServiceDTO.setDescription(subService.getDescription());
                    subServiceDTO.setBasePrice(subService.getBasePrice());
                    return subServiceDTO;
                })
                .collect(Collectors.toList()); // ✅ Fix here

        dto.setSubServices(subServices);
        return dto;
    }
}
