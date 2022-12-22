package sa.aref.service.duty;

import org.springframework.stereotype.Service;
import sa.aref.entity.duties.MainDuties;
import sa.aref.repository.duty.MainDutyRepository;

@Service
public class MainDutyService {
    private final MainDutyRepository mainDutyRepository;

    public MainDutyService(MainDutyRepository mainDutyRepository) {
        this.mainDutyRepository = mainDutyRepository;
    }
    public void addMainDuty(MainDuties mainDuty) {
        mainDutyRepository.save(mainDuty);
    }
}
