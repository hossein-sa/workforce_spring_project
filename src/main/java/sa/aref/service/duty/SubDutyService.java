package sa.aref.service.duty;

import org.springframework.stereotype.Service;
import sa.aref.entity.duties.SubDuties;
import sa.aref.repository.duty.SubDutyRepository;

@Service
public class SubDutyService {
    private final SubDutyRepository subDutyRepository;

    public SubDutyService(SubDutyRepository subDutyRepository) {
        this.subDutyRepository = subDutyRepository;
    }

    public void addSubDuty(SubDuties subDuty) {
        subDutyRepository.save(subDuty);
    }
}
