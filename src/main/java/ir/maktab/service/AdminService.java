package ir.maktab.service;

import ir.maktab.model.entity.Admin;
import ir.maktab.model.entity.VerificationToken;
import ir.maktab.model.repository.AdminRepository;
import ir.maktab.model.repository.UserRepository;
import ir.maktab.model.repository.UserSpecifications;
import ir.maktab.model.entity.User;
import ir.maktab.util.StatusType;
import ir.maktab.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {

    private UserRepository userRepository;

    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;


    @Autowired
    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setAdminRepository(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Transactional
    public Admin save(User user) {
        Admin admin = new Admin(user);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }


    public Page<User> findMaxMatch(String name,
                                   String family,
                                   String email,
                                   UserRole role,
                                   int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return userRepository.findAll(UserSpecifications.findMaxMatch(name, family, email, role), pageable);
    }


    public List<User> findByStatus(StatusType statusType) {
        return userRepository.findByStatus(statusType);
    }

    public void editUser(User user) {

    }



    public boolean sendMail(Admin admin) {
       return userService.sendTo(admin);
    }
}
