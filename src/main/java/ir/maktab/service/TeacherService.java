package ir.maktab.service;

import ir.maktab.model.entity.Teacher;
import ir.maktab.model.entity.User;
import ir.maktab.model.repository.CourseRepository;
import ir.maktab.model.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeacherService {

    private TeacherRepository teacherRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private CourseRepository courseRepository;

    @Autowired
    public void setTeacherRepository(TeacherRepository teacherRepository, PasswordEncoder passwordEncoder
            , UserService userService, CourseRepository courseRepository) {
        this.passwordEncoder = passwordEncoder;
        this.teacherRepository = teacherRepository;
        this.userService = userService;
        this.courseRepository = courseRepository;
    }

    @Modifying
    @Transactional
    public Teacher save(User user) {
        Teacher teacher = new Teacher(user);
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        return teacherRepository.save(teacher);
    }


    public Teacher getTeacherByEmail(String email){
        return teacherRepository.findByEmail(email);
    }


public Teacher getTeacher(Integer id) throws Exception {
    try {
        return teacherRepository.findById(id);
    } catch (Exception e){
        throw new Exception("finding teacher failed");
    }
}

    public void sendMail(Teacher teacher) {
        userService.sendTo(teacher);
    }


}
