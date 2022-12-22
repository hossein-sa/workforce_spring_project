package sa.aref.service.accounts;

import org.springframework.stereotype.Service;
import sa.aref.repository.accounts.ExpertRepository;

@Service
public class ExpertService {
    private final ExpertRepository expertRepository;

    public ExpertService(ExpertRepository expertRepository) {
        this.expertRepository = expertRepository;
    }
}
