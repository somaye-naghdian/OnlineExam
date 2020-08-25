package ir.maktab.controller;

import ir.maktab.model.entity.Course;
import ir.maktab.model.entity.User;
import ir.maktab.service.CourseService;
import ir.maktab.service.UserService;
import net.minidev.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class CourseRestController {

    private UserService userService;
    private CourseService courseService;
    private ModelMapper modelMapper;
    Set<User> users = new HashSet<>();

    @Autowired
    public CourseRestController(UserService userService, CourseService courseService, ModelMapper modelMapper) {
        this.userService = userService;
        this.courseService = courseService;
        this.modelMapper = modelMapper;
    }


    @GetMapping(value = "/addUserToCourseRest/{courseTitle}/{userId}")
    public ResponseEntity addUserToCourse(@PathVariable("courseTitle") String courseTitle,
                                          @PathVariable("userId") String userId) {
        User user = userService.findById(Integer.parseInt(userId));
        System.out.println(userId);
        users.add(user);
        Course course = courseService.findCourseByTitle(courseTitle);
        System.out.println(courseTitle);
        course.addUser(user);
        System.out.println(course);
        courseService.updateCourseUsers(users,course.getCourseTitle());
        try {
            return ResponseEntity.ok()
                    .body("user" + user.getName() + "add to " + courseTitle);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body("error " + e.getMessage());
        }
    }

    @GetMapping(value = "/userCourseProcess/{courseTitle}", consumes = "application/json", produces = "text/html")
    public ResponseEntity<List<JSONObject>> getUserOfCourseProcess(@PathVariable("courseTitle") String courseTitle) {
        List<User> userOfCourse = courseService.getUserOfCourse(courseTitle);
        List<JSONObject> entities = new ArrayList<>();
        for (User user : userOfCourse) {
            JSONObject entity = new JSONObject();
            entity.put("id", user.getId());
            entity.put("name", user.getName());
            entity.put("family", user.getFamily());
            entity.put("role", user.getRole());
            entity.put("email", user.getEmail());
            entities.add(entity);
        }
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }
}
