package ir.maktab.service;

import ir.maktab.exceptions.CourseAlreadyExist;
import ir.maktab.model.entity.User;
import ir.maktab.model.repository.CourseRepository;
import ir.maktab.model.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void addNewCourse(Course course) throws CourseAlreadyExist {
        courseRepository.save(course);
        //todo i duplicate
    }

    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    public Course findCourseByTitle(String title) {
        //todo if not exist course with this title
       return courseRepository.findByCourseTitle(title);
    }

    @Transactional
    public void updateCourseUsers(Set<User> users, String courseTitle){
        courseRepository.updateCourse(users,courseTitle);
    }

    public List<User> getUserOfCourse(String courseTitle){
         return courseRepository.findUsersByCourseTitle(courseTitle);
    }


}
