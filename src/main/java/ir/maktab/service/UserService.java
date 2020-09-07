package ir.maktab.service;

import ir.maktab.exceptions.UserAlreadyExistsException;
import ir.maktab.exceptions.UserNotFoundException;
import ir.maktab.model.dto.UserDto;
import ir.maktab.model.entity.*;
import ir.maktab.model.repository.AdminRepository;
import ir.maktab.model.repository.TeacherRepository;
import ir.maktab.model.repository.UserRepository;
import ir.maktab.model.repository.UserSpecifications;
import ir.maktab.util.Mapper;
import ir.maktab.util.StatusType;
import ir.maktab.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private VerificationTokenService verificationTokenService;
    private MailService mailService;
    private Mapper mapper;


    @Autowired
    public UserService(UserRepository userRepository, MailService mailService
            , VerificationTokenService verificationTokenService,
                       Mapper mapper) {
        this.userRepository = userRepository;
        this.mailService = mailService;
        this.verificationTokenService = verificationTokenService;
        this.mapper = mapper;
    }


   public List<UserDto> findAllStudents(){
       List<User> students = userRepository.findByRole(UserRole.STUDENT);
       List<UserDto> userDtoList = mapper.convertEntityListToDto(students);
       return userDtoList;
   }
   public List<User> findAllTeachers(){
       return userRepository.findByRole(UserRole.TEACHER);

   }

   public User save(User user){
      return   userRepository.save(user);
   }


//   public List<Course> getUserCourses(String email){
//      return   userRepository.findUserCourse(email);
//    }


    @Transactional
    public void registerNewUser(User user) {
//        try {
//         //   User found = userRepository.findByEmail(user.getEmail());
//        }catch (NullPointerException e){
//            e.
//        }

//        user.setStatus(StatusType.INACTIVE);
//        //   if (found == null) {
//        if (authenticationService.isUserInformationTrue(user)) {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            UserRole userRole = user.getRole();
//            switch (userRole) {
//                case ADMIN:
//                   return adminService.save(getAdminFromUser(user));
//                    break;
//                case TEACHER:
//                   return teacherService.save(getTeacherFromUser(user));
//                    break;
//                case STUDENT:
//                   return studentService.save(getStudentFromUser(user));
//                    break;
//            }
//            //   sendTo(user);
//        } else
//            throw new UserAlreadyExistsException("duplicate email!");
//        //   }

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


    public boolean sendTo(User user) {

        VerificationToken verificationToken = new VerificationToken(user);
        verificationTokenService.save(verificationToken);
        String mailText = "To confirm your account, please click here : "
                + "http://localhost:8080/verify?token=" + verificationToken.getToken();
        return mailService.sendMail(user.getEmail(), mailText, "Account Verification");
    }


}
