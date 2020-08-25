package ir.maktab.service;

import ir.maktab.model.repository.UserRepository;
import ir.maktab.model.repository.UserSpecifications;
import ir.maktab.model.entity.User;
import ir.maktab.util.StatusType;
import ir.maktab.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private UserRepository userRepository;

    @Autowired
    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> findMaxMatch(String name,
                                   String family,
                                   String email,
                                   UserRole role,
                                   int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return userRepository.findAll(UserSpecifications.findMaxMatch(name, family, email, role), pageable);
    }

    public List<User> findByStatus(StatusType statusType){
        return userRepository.findByEnabled(statusType);
    }

    public void editUser(User user) {

    }
}
