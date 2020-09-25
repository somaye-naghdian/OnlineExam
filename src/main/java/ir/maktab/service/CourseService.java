package ir.maktab.service;

import ir.maktab.exceptions.CourseAlreadyExist;
import ir.maktab.exceptions.UserNotFoundException;
import ir.maktab.model.dto.CourseDto;
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

    @Autowired
    public CourseService(CourseRepository courseRepository
            , StudentService studentService, Mapper mapper,
                         UserService userService) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
        this.mapper = mapper;
        this.userService = userService;
    }


    @Transactional
    public Course save(Course course) throws CourseAlreadyExist {
        return courseRepository.save(course);
    }

    public Course createNewCourse(CourseDto courseDto, Classification classification) {
        String courseTitle = courseDto.getCourseTitle();
        Course course = new Course();
        course.setCourseTitle(courseTitle);
        course.setClassification(classification);
        return save(course);
    }

    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    public Course findCourseByTitle(String title) {

        return courseRepository.findByCourseTitle(title);
    }

    public List<User> getStudentsOfCourse(String courseTitle) {
        List<User> allStudents = courseRepository.findUsersByCourseTitle(courseTitle);
       List<User> students=new ArrayList<>();
        for (User user:
             allStudents) {
            if(user.getRole().equals(UserRole.STUDENT)){
                students.add(user);
            }
        }
        return students;
    }

    @Transactional
    public void addUserToCourse(String courseTitle, String email) {
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
        if(course.getUserList().isEmpty() ||(! course.getUserList().contains(user))){
            throw new UserNotFoundException("not Found");
        }else{
            course.getUserList().remove(user);
            user.getCourseList().remove(course);
            userService.save(user);
            courseRepository.save(course);
        }
    }

    public List<Exam> getExamsOfCourse(String courseTitle) {
        Course course = findCourseByTitle(courseTitle);
        List<Exam> examList = course.getExamList();
        return examList;
    }

    public Set<Course> getUserCourses(User user) {
        List<Course> courseList = user.getCourseList();
        Set<Course> courses = new HashSet<>();
        for (Course course : courseList)
            courses.add(course);
        return courses;
    }

    public List<Course> getStudentCourses(String email){
        Student student = studentService.getStudentByEmail(email);
        List<Course> allCourse = getAllCourse();
        List<Course> studentUserList =new ArrayList<>();
        for (Course course:
             allCourse) {
            if( course.getUserList().contains(student)){
                studentUserList.add(course);
            }
        }
        return studentUserList;
    }
}


