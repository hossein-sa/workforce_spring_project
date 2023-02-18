package sa.aref.service.duty;

import org.springframework.stereotype.Service;
import sa.aref.entity.duties.SubDuties;
import sa.aref.exception.CustomExceptionIsExist;
import sa.aref.repository.duty.SubDutyRepository;

@Service
public class SubDutyService {
    private final SubDutyRepository subDutyRepository;
    private final MainDutyService mainDutyService;

    public SubDutyService(SubDutyRepository subDutyRepository, MainDutyService mainDutyService) {
        this.subDutyRepository = subDutyRepository;
        this.mainDutyService = mainDutyService;
    }


    public void addSubDuty(SubDuties subDuty) {
        if (subDutyRepository.existsByName(subDuty.getName()))
            throw new CustomExceptionIsExist("Sub duty is exist!");
        if (!mainDutyService.existsByName(subDuty.getMainDuties().getName()))
            throw new CustomExceptionIsExist("Main Duty is not exist");
        subDutyRepository.save(subDuty);
    }

//    public List<SubDuties> findByMainDutiesId(Long id) {
//        return subDutyRepository.findByMainDutiesId(id);
//    }
//
//    public void changeDutyPrice(Long subDutyId, Double price) {
//        subDutyRepository.changeDutyPrice(subDutyId, price);
//    }

}
