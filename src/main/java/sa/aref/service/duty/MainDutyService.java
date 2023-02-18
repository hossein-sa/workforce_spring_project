package sa.aref.service.duty;

import org.springframework.stereotype.Service;
import sa.aref.entity.duties.MainDuties;
import sa.aref.exception.CustomExceptionIsExist;
import sa.aref.repository.duty.MainDutyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MainDutyService {
    private final MainDutyRepository mainDutyRepository;

    public MainDutyService(MainDutyRepository mainDutyRepository) {
        this.mainDutyRepository = mainDutyRepository;
    }

    public boolean addMainDuty(MainDuties mainDuty) {
        try {
            mainDutyRepository.save(mainDuty);
            return true;
        } catch (CustomExceptionIsExist e) {
            return false;
        }
    }

    public List<MainDuties> getMainDuties() {
        return mainDutyRepository.findAll();
    }

    public Optional<MainDuties> findById(int id) {
        return mainDutyRepository.findById(id);
    }

    public Optional<MainDuties> findByName(String name) {
        return mainDutyRepository.findByName(name);
    }

    public boolean existsByName(String name) {
        return mainDutyRepository.existsByName(name);
    }
}
