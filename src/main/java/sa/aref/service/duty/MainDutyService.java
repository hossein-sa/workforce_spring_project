package sa.aref.service.duty;

import org.springframework.stereotype.Service;
import sa.aref.entity.duties.MainDuty;
import sa.aref.repository.duty.MainDutyRepository;

import java.util.List;

@Service
public class MainDutyService {
    private final MainDutyRepository mainDutyRepository;

    public MainDutyService(MainDutyRepository mainDutyRepository) {
        this.mainDutyRepository = mainDutyRepository;
    }

    public boolean addMainDuty(MainDuty mainDuty) {
        try{
            mainDutyRepository.save(mainDuty);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<MainDuty> getMainDuties() {
        return mainDutyRepository.findAll();
    }

}
