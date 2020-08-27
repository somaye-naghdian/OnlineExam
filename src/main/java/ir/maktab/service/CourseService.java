package ir.maktab.service;

import ir.maktab.exceptions.CourseAlreadyExist;
import ir.maktab.model.entity.User;
import ir.maktab.model.repository.CourseRepository;
import ir.maktab.model.entity.Course;
import ir.maktab.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public void save(Course course) throws CourseAlreadyExist {
        courseRepository.save(course);
    }

    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    public Course findCourseByTitle(String title) {

        return courseRepository.findByCourseTitle(title);
    }

    public Set<User> getStudentOfCourse(String courseTitle) {
        Set<User> allUsers = courseRepository.findUsersByCourseTitle(courseTitle);
        Set<User> users=new HashSet<>();
        for (User user:
             allUsers) {
            if(! user.getRole().equals(UserRole.ADMIN)){
                users.add(user);
            }
        }
        return  users;
    }

    @Transactional
    public void addUserToCourse(Course course, User user) throws Exception {
        if(course.getUserList().contains(user)){
            throw new Exception("duplicate user");
        }
        course.getUserList().add(user);
        save(course);
    }

    @Transactional
    public void deleteToCourse(Course course, User user) {
        boolean remove = course.getUserList().remove(user);
        if (remove) {
            courseRepository.save(course);
        }
    }

}
