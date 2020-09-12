package ir.maktab.controller;

import ir.maktab.model.entity.Student;
import ir.maktab.model.entity.User;
import ir.maktab.service.CourseService;
import ir.maktab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseRestController {

    private UserService userService;
    private CourseService courseService;


    @Autowired
    public CourseRestController(UserService userService, CourseService courseService
    ) {
        this.userService = userService;
        this.courseService = courseService;

    }

//    @PutMapping(value = "/addUserToCourseRest/{courseTitle}/{email}")
//    public ResponseEntity addUserToCourse(@PathVariable("courseTitle") String courseTitle,
//                                          @PathVariable("email") String email) {
//
//        try {
//            courseService.addUserToCourse(courseTitle, email);
//            return ResponseEntity.ok()
//                    .body("user added to course");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest()
//                    .body("error " + e.getMessage());
//        }
//    }

//    @DeleteMapping(value = "/deleteUserFromCourse/{courseTitle}/{userId}")
//    public ResponseEntity deleteUserOfCourse(@PathVariable("courseTitle") String courseTitle,
//                                             @PathVariable("userId") String userId) {
//        User user = userService.findById(Integer.parseInt(userId));
//        Course course = courseService.findCourseByTitle(courseTitle);
//
//        try {
//            courseService.deleteToCourse(course, user);
//            return ResponseEntity.ok()
//                    .body("user deleted of course");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest()
//                    .body("error " + e.getMessage());
//        }
//    }

//    @GetMapping(value = "/showAllStudentOfCourse/{courseTitle}", consumes = "application/json", produces = "text/html")
//    public List<User> getAllStudentsOfCourse(@PathVariable("courseTitle") String courseTitle) {
//
//        try {
//            return  courseService.getStudentsOfCourse(courseTitle);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }





 /*   @GetMapping(value = "/getExamsOfCourseProcess/{courseTitle}", produces = "text/html")
    @ResponseBody
    public List<Exam> getExamsOfCourse(@PathVariable("courseTitle") String courseTitle) {
       // ModelAndView modelAndView = new ModelAndView("teacher_showExam");
        try {
           return courseService.getExamsOfCourse(courseTitle);
         //   modelAndView.addObject("examsOfCourse", examsOfCourse);
        } catch (Exception e) {
        //    modelAndView = new ModelAndView("simpleMessage", "message", e.getMessage());
            return null;
        }
    }*/

}
