package sa.aref.service.accounts;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import sa.aref.entity.accounts.ExpertAccount;
import sa.aref.exception.CustomExceptionInvalid;
import sa.aref.exception.CustomExceptionIsExist;
import sa.aref.exception.CustomExceptionNotFound;
import sa.aref.repository.accounts.ExpertRepository;
import sa.aref.utils.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Service
public class ExpertService {
    private final ExpertRepository expertRepository;

    public ExpertService(ExpertRepository expertRepository) {
        this.expertRepository = expertRepository;
    }

    @Transactional
    public void register(ExpertAccount expertAccount) throws CustomExceptionIsExist {
        if(expertRepository.findByEmail(expertAccount.getEmail()).isPresent()) {
            throw new CustomExceptionIsExist("Email already exists");
        }

        expertRepository.save(expertAccount);
    }


    public void changePassword(int expertId, String currentPassword, String newPassword) throws CustomExceptionNotFound, CustomExceptionInvalid {
        ExpertAccount expertAccount = expertRepository.findById(expertId)
                .orElseThrow(() -> new CustomExceptionNotFound("Expert not found"));

        if (!expertAccount.getPassword().equals(currentPassword)) {
            throw new CustomExceptionInvalid("Current password is invalid");
        }

        expertAccount.setPassword(newPassword);
        expertRepository.save(expertAccount);
    }

    public void setProfilePhoto(Integer id, byte[] photoBytes) throws IOException {
        ExpertAccount expertAccount = expertRepository.findById(id).orElseThrow(() -> new CustomExceptionNotFound("ExpertAccount not found with id " + id));
        String photoPath = "C:/profiles/" + id + ".jpg";
        File photoFile = FileUtil.fileWriter(Path.of(photoPath), photoBytes);
        expertAccount.setProfilePhoto(FileUtil.imageConverter(photoFile));
        expertRepository.save(expertAccount);
    }

}
