package ir.maktab.controller;

import ir.maktab.model.dto.ExamDto;
import ir.maktab.model.entity.Course;
import ir.maktab.model.entity.Exam;
import ir.maktab.model.entity.User;
import ir.maktab.service.CourseService;
import ir.maktab.service.ExamService;
import ir.maktab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ExamController {

    private ExamService examService;
    private CourseService courseService;
    private UserService userService;

    @Autowired
    public ExamController(ExamService examService, CourseService courseService
            , UserService userService) {
        this.examService = examService;
        this.courseService = courseService;
        this.userService = userService;
    }

    @GetMapping(value = "newExam")
    public ModelAndView getNewExam(@ModelAttribute("course") String courseTitle,
                                   HttpServletRequest request) {
        String userEmail = request.getParameter("user");
        System.out.println(userEmail);
        User user = userService.findUserByEmail(userEmail);
        Course course = courseService.findCourseByTitle(courseTitle);
        ModelAndView modelAndView = new ModelAndView("teacher_addExam");
        modelAndView.addObject("user", user);
        modelAndView.addObject("course", course);
        return modelAndView;
    }

    @PostMapping(value = "/createNewExam/{course}/{user}", consumes = "application/json")
    public ResponseEntity createNewExam(@RequestBody ExamDto examDto,
                                        @PathVariable("course") String courseTitle
            , @PathVariable("user") String userId) {

        try {

            examService.saveExam(examDto,Integer.parseInt(userId),courseTitle);
            return ResponseEntity.ok()
                    .body("Exam saved with title:" + examDto.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body("error " + e.getMessage());
        }
    }




    @PutMapping(value = "/editExam/{course}/{id}", consumes = "application/json")
    public ResponseEntity updateExam(@RequestBody ExamDto examDto
            , @PathVariable("id") String userId) {

        try {
                examService.updateExam(examDto, Integer.parseInt(userId));
                return ResponseEntity.ok()
                        .body("Exam update with title:" + examDto.getTitle());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body("error " + e.getMessage());
        }
    }

    @PutMapping(value = "/stopExam/{title}/{id}")
    public ResponseEntity stopExam(@PathVariable("title") String title
    ,@PathVariable("id") String id) {
        try {
            examService.stopExam(title,id);
            return ResponseEntity.ok()
                    .body("exam stopped");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body("error " + e.getMessage());
        }
    }

//    @DeleteMapping(value = "/deleteExam")
//    public ResponseEntity deleteExam(@RequestBody ExamDto examDto){
//
//    }

}
