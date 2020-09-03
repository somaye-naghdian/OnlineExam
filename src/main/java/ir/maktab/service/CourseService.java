package ir.maktab.service;

import ir.maktab.exceptions.CourseAlreadyExist;
import ir.maktab.model.entity.*;
import ir.maktab.model.repository.CourseRepository;
import ir.maktab.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CourseService {

    private CourseRepository courseRepository;
    private UserService userService;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Modifying
    @Transactional
    public Course save(Course course) throws CourseAlreadyExist {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    public Course findCourseByTitle(String title) {

        return courseRepository.findByCourseTitle(title);
    }

    public Set<User> getUserOfCourse(String courseTitle) {
        List<User> allUsers = courseRepository.findUsersByCourseTitle(courseTitle);
        Set<User> users = new HashSet<>();
        for (User user :
                allUsers) {
            if (!user.getRole().equals(UserRole.ADMIN)) {
                users.add(user);
            }
        }
        return users;
    }



    @Transactional
    public void addUserToCourse(String courseTitle, String email)  {
        Course course = findCourseByTitle(courseTitle);
        User user = userService.findUserByEmail(email);
        System.out.println(user);
        course.getUserList().add(user);
        save(course);
    }

    @Transactional
    public void deleteToCourse(Course course, User user) {

        course.getUserList().remove(user);
        save(course);
    }

    public List<Exam> getExamsOfCourse(String courseTitle){
        List<Exam> examOfCourse = courseRepository.findExamOfCourse(courseTitle);
        System.out.println(examOfCourse);
        return  examOfCourse;
    }
}


