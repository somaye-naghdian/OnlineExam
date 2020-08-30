package ir.maktab.service;

import ir.maktab.model.entity.Course;
import ir.maktab.model.entity.Teacher;
import ir.maktab.model.entity.User;
import ir.maktab.model.repository.CourseRepository;
import ir.maktab.model.repository.TeacherRepository;
import ir.maktab.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherService {

    private TeacherRepository teacherRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private CourseRepository courseRepository;

    @Autowired
    public void setTeacherRepository(TeacherRepository teacherRepository, PasswordEncoder passwordEncoder
            , UserService userService,CourseRepository courseRepository) {
        this.passwordEncoder = passwordEncoder;
        this.teacherRepository = teacherRepository;
        this.userService = userService;
        this.courseRepository=courseRepository;
    }

    @Transactional
    public Teacher save(User user) {
        Teacher teacher = new Teacher(user);
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        return teacherRepository.save(teacher);
    }

//    public List<Course> getTeacherCourses(String email) {
//   //     User teacher = userService.findUserByEmail(email);
//        return teacherRepository.findByEmail(email);
//    }


    public void sendMail(Teacher teacher) {
        userService.sendTo(teacher);
    }


}
