package sa.aref.service.admin;

import org.springframework.stereotype.Service;
import sa.aref.entity.admin.Admin;
import sa.aref.exception.CustomExceptionInvalid;
import sa.aref.repository.admin.AdminRepository;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void addAdmin(Admin admin) {
        if (adminRepository.existsByEmail(admin.getEmail()))
            throw new CustomExceptionInvalid("this email is already exist");

    }
}
