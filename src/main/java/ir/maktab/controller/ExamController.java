package ir.maktab.controller;

import ir.maktab.model.entity.Course;
import ir.maktab.model.entity.Exam;
import ir.maktab.model.entity.User;
import ir.maktab.service.CourseService;
import ir.maktab.service.ExamService;
import ir.maktab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
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

    @RequestMapping(value = "newExam", method = RequestMethod.GET)
    public ModelAndView getNewExam(@RequestParam("courseTitle") String courseTitle,
                                   @RequestParam("userEmail") String email) {
        System.out.println(email);
        User user = userService.findUserByEmail(email);
        Course course = courseService.findCourseByTitle(courseTitle);
        ModelAndView modelAndView = new ModelAndView("teacher_addExam");
        modelAndView.addObject("user", user);
        modelAndView.addObject("course", course);
        return modelAndView;
    }


    @RequestMapping(value = {"/editExam", "/deleteExam"}, method = RequestMethod.GET)
    public ModelAndView updateExam(HttpServletRequest request
            , @RequestParam("startDate") String startDate
            , @RequestParam("endDate") String endDate
            , @RequestParam("id") String examId) {
        String requestedValue = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        String examId2 = request.getParameter("id");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String time = request.getParameter("time");
        String teacher_email = request.getParameter("teacher");
        ModelAndView modelAndView = new ModelAndView("simpleMessage");
        try {
            if (requestedValue.equals("/editExam")) {
                Exam exam = examService.updateExam(examId, title, description, startDate, endDate, time, teacher_email);
                System.out.println(exam);
                modelAndView.addObject("message", "exam " + exam.getTitle() + " successfully updated");
            } else if (requestedValue.equals("/deleteExam")) {
                examService.deleteExam(Integer.parseInt(examId),teacher_email );
                modelAndView.addObject("message", "exam successfully deleted");
            }

        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.addObject("message", e.getMessage());
        }
        return modelAndView;
    }

}
