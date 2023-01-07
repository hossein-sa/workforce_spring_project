package sa.aref.service.duty;

import org.springframework.stereotype.Service;
import sa.aref.entity.duties.SubDuties;
import sa.aref.repository.duty.SubDutyRepository;

import java.util.List;

@Service
public class SubDutyService {
    private final SubDutyRepository subDutyRepository;

    public SubDutyService(SubDutyRepository subDutyRepository) {
        this.subDutyRepository = subDutyRepository;
    }

    public boolean addSubDuty(SubDuties subDuty) {
        try {
            subDutyRepository.save(subDuty);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<SubDuties> findByMainDutiesId(Long id) {
        return subDutyRepository.findByMainDutiesId(id);
    }

    public void changeDutyPrice(Long subDutyId, Double price) {
        subDutyRepository.changeDutyPrice(subDutyId, price);
    }

}
