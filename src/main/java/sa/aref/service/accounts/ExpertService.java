package sa.aref.service.accounts;

import org.springframework.stereotype.Service;
import sa.aref.entity.accounts.ExpertAccount;
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

    public void register(ExpertAccount expertAccount) {
        if (expertRepository.existsByEmail(expertAccount.getEmail()))
            throw new CustomExceptionIsExist("This email account is exist");
        expertRepository.save(expertAccount);
    }

    public void changePassword(Integer id, String password) {
        expertRepository.changePassword(id, password);
    }

    public void setProfilePhoto(Integer id, byte[] photoBytes) throws IOException {
        ExpertAccount expertAccount = expertRepository.findById(id).orElseThrow(() -> new CustomExceptionNotFound("ExpertAccount not found with id " + id));
        String photoPath = "C:/profiles/" + id + ".jpg";
        File photoFile = FileUtil.fileWriter(Path.of(photoPath), photoBytes);
        expertAccount.setProfilePhoto(FileUtil.imageConverter(photoFile));
        expertRepository.save(expertAccount);
    }

}
