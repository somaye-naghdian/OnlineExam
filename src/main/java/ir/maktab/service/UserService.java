package ir.maktab.service;

import ir.maktab.exceptions.UserAlreadyExistsException;
import ir.maktab.exceptions.UserNotFoundException;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void registerNewUser(User user) throws UserAlreadyExistsException {
        User found = userRepository.findByEmail(user.getEmail());
        if (found == null) {
            userRepository.save(user);
        } else
            throw new UserAlreadyExistsException("duplicate email!");
    }

    public User findById(Integer id) {
        return userRepository.findById(id);
    }

    public UserRole authenticationUser(String email, String password) throws UserNotFoundException {
        try {
            User user = userRepository.findByEmail(email);
            String userPassword = user.getPassword();
            if (userPassword.equals(password)) {
                return user.getRole();
            }
        } catch (NullPointerException ne) {
            throw new UserNotFoundException("Not Found");
        }
        return null;
    }

    @Transactional(readOnly = true)
    public List<User> showAllUser() {
        List<User> userList = userRepository.findAll();
        List<User> users = new ArrayList<>();
        for (User user :
                userList) {
            if (!user.getRole().equals(UserRole.ADMIN)) {
                users.add(user);
            }
        }
        return users;
    }

    @Transactional
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Page<User> findMaxMatch(String name,
                                   String family,
                                   String email,
                                   UserRole role,
                                   int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return userRepository.findAll(UserSpecifications.findMaxMatch(name, family, email, role), pageable);
    }


    @Transactional
    public void updateUser(StatusType statusType, String email) {
        userRepository.updateUserEnabled(statusType, email);
    }

    @Transactional
    public void updateUser(User user) {
        userRepository.updateUser(user.getName(), user.getFamily(), user.getStatus()
                , user.getRole(), user.getEmail());
    }





//    @Transactional
//    public void addCourseUser(User user, Course course){
//        user.getCourseList().add(course);
//        userRepository.updateUserCourse(user.getCourseList(),user.getId());
//    }
}
