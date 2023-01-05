package sa.aref.service.accounts;

import org.springframework.stereotype.Service;
import sa.aref.entity.accounts.ExpertAccount;
import sa.aref.entity.order.Order;
import sa.aref.repository.accounts.ExpertRepository;

import java.util.List;

@Service
public class ExpertService {
    private final ExpertRepository expertRepository;

    public ExpertService(ExpertRepository expertRepository) {
        this.expertRepository = expertRepository;
    }

    public ExpertAccount registerExpert(ExpertAccount expert) {
        return expertRepository.save(expert);
    }

    public void changePassword(Integer id, String password) {
        expertRepository.changePassword(id, password);
    }


}
