package ir.maktab.service;

import ir.maktab.exceptions.CourseAlreadyExist;
import ir.maktab.model.dto.UserDto;
import ir.maktab.model.entity.*;
import ir.maktab.model.repository.CourseRepository;
import ir.maktab.util.Mapper;
import ir.maktab.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CourseService {

    private CourseRepository courseRepository;
    private StudentService studentService;
    private Mapper mapper;
    private UserService userService;
    public List<Student> studentList = new ArrayList<>();

    @Autowired
    public CourseService(CourseRepository courseRepository
            , StudentService studentService, Mapper mapper,
                         UserService userService) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
        this.mapper = mapper;
        this.userService = userService;
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

    public List<Student> getStudentsOfCourse(String courseTitle) {
        List<Student> allStudents = courseRepository.findUsersByCourseTitle(courseTitle);
        return allStudents;
    }

    @Transactional
    public void addStudentToCourse(String courseTitle, String email) {
        User user = userService.findUserByEmail(email);
        Course course = findCourseByTitle(courseTitle);
        course.getUserList().add(user);
        save(course);
    }

    @Transactional
    public void addTeacherToCourse(String courseTitle, String email) {
        User user = userService.findUserByEmail(email);
        Course course = findCourseByTitle(courseTitle);
        if (course.getUserList().isEmpty()) {
            List<User> userList = new ArrayList<>();
            userList.add(user);
            course.setUserList(userList);
        } else {
            List<User> userList = course.getUserList();
            userList.add(user);
            course.setUserList(userList);
        }
        user.getCourseList().add(course);
        userService.save(user);
        courseRepository.save(course);
    }


    @Transactional
    public void deleteStudentFromCourse(String courseTitle, String email) {
        User user = userService.findUserByEmail(email);
        Course course = findCourseByTitle(courseTitle);
        course.getUserList().remove(user);
        save(course);
    }

    public List<Exam> getExamsOfCourse(String courseTitle) {
        List<Exam> examOfCourse = courseRepository.findExamOfCourse(courseTitle);
        System.out.println(examOfCourse);
        return examOfCourse;
    }

public Set<Course> getUserCourses(User user){
    List<Course> courseList = user.getCourseList();
    Set<Course> courses = new HashSet<>();
    for (Course course : courseList)
        courses.add(course);
    return courses;
}
}


