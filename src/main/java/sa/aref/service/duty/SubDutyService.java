package sa.aref.service.duty;

import org.springframework.stereotype.Service;
import sa.aref.entity.accounts.ExpertAccount;
import sa.aref.entity.duties.SubDuties;
import sa.aref.exception.CustomExceptionIsExist;
import sa.aref.exception.CustomExceptionNotFound;
import sa.aref.repository.accounts.ExpertRepository;
import sa.aref.repository.duty.SubDutyRepository;

@Service
public class SubDutyService {
    private final SubDutyRepository subDutyRepository;
    private final MainDutyService mainDutyService;
    private final ExpertRepository expertRepository;

    public SubDutyService(SubDutyRepository subDutyRepository, MainDutyService mainDutyService, ExpertRepository expertRepository) {
        this.subDutyRepository = subDutyRepository;
        this.mainDutyService = mainDutyService;
        this.expertRepository = expertRepository;
    }


    public void addSubDuty(SubDuties subDuty) {
        if (subDutyRepository.existsByName(subDuty.getName()))
            throw new CustomExceptionIsExist("Sub duty is exist!");
        if (!mainDutyService.existsByName(subDuty.getMainDuties().getName()))
            throw new CustomExceptionIsExist("Main Duty is not exist");
        subDutyRepository.save(subDuty);
    }

    public SubDuties addExpertToSubDuties(Integer subDutiesId, Integer expertId) {
        SubDuties subDuties = subDutyRepository.findById(subDutiesId)
                .orElseThrow(() -> new CustomExceptionNotFound("SubDuties not found with id: " + subDutiesId));
        ExpertAccount expert = expertRepository.findById(expertId)
                .orElseThrow(() -> new CustomExceptionNotFound("ExpertAccount not found with id: " + expertId));
        subDuties.getExpertAccounts().add(expert);
        subDutyRepository.save(subDuties);
        return subDuties;
    }

    public SubDuties removeExpertOfSubDuties(Integer subDutiesId, Integer expertId) {
        SubDuties subDuties = subDutyRepository.findById(subDutiesId)
                .orElseThrow(() -> new CustomExceptionNotFound("SubDuties not found with id: " + subDutiesId));
        ExpertAccount expert = expertRepository.findById(expertId)
                .orElseThrow(() -> new CustomExceptionNotFound("ExpertAccount not found with id: " + expertId));
        subDuties.getExpertAccounts().remove(expert);
        subDutyRepository.save(subDuties);
        return subDuties;
    }

//    public List<SubDuties> findByMainDutiesId(Long id) {
//        return subDutyRepository.findByMainDutiesId(id);
//    }
//
//    public void changeDutyPrice(Long subDutyId, Double price) {
//        subDutyRepository.changeDutyPrice(subDutyId, price);
//    }

}
