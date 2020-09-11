package ir.maktab.controller;

import ir.maktab.model.dto.ExamDto;
import ir.maktab.model.dto.QuestionDto;
import ir.maktab.model.entity.Course;
import ir.maktab.model.entity.Exam;
import ir.maktab.model.entity.User;
import ir.maktab.service.CourseService;
import ir.maktab.service.ExamService;
import ir.maktab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

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
                                   @RequestParam("userEmail") String email,
                                   Model model) {
        System.out.println(email);
        User user = userService.findUserByEmail(email);
        Course course = courseService.findCourseByTitle(courseTitle);
        ModelAndView modelAndView = new ModelAndView("teacher_addExam");
        modelAndView.addObject("user", user);
        modelAndView.addObject("exam", new ExamDto());
        modelAndView.addObject("course", course);
        modelAndView.addObject("message", model.getAttribute("message"));
        return modelAndView;
    }

    @RequestMapping(value = "/addNewExam", method = RequestMethod.POST)
    public String createNewExam(@ModelAttribute("exam") ExamDto examDto,
                                @RequestParam("courseTitle") String courseTitle
            , @RequestParam("user") String userId, Model model) {
        try {
            System.out.println(examDto);
            examService.saveExam(examDto, Long.valueOf((userId)), courseTitle);
            model.addAttribute("message", "Exam added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", e.getMessage());
        }
        return "teacher_addExam";
    }

    @RequestMapping(value = {"/editExam", "/deleteExam", "/stopExam"}, method = RequestMethod.GET)
    public String updateExam(@ModelAttribute("exam") ExamDto examDto
            , HttpServletRequest request, Model model, @RequestParam("user") String teacher) {
        String requestedValue = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);

        ModelAndView modelAndView = new ModelAndView("simpleMessage");
        try {
            if (requestedValue.equals("/editExam")) {
                Exam exam = examService.updateExam(examDto, teacher);
                model.addAttribute("message", "exam " + exam.getTitle() + " successfully updated");
            }
            else if (requestedValue.equals("/deleteExam")) {
                examService.deleteExam(Long.valueOf((examDto.getId())), teacher);
                model.addAttribute("message", "exam successfully deleted");
            }
            else if (requestedValue.equals("/stopExam")) {
                examService.stopExam(examDto, teacher);
                model.addAttribute("message", "exam successfully stop");
            }

        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.addObject("message", e.getMessage());
        }
        return "teacher_addExam";
    }


    @RequestMapping(value = "/examsOfCourse", method = RequestMethod.GET)
    public ModelAndView getExamsOfCourse(@RequestParam("courseTitle") String courseTitle
            , @RequestParam("user") String email) {
        List<Exam> examsOfCourse = courseService.getExamsOfCourse(courseTitle);
        User user = userService.findUserByEmail(email);
        ModelAndView modelAndView = new ModelAndView("student_exams", "examsOfCourse", examsOfCourse);
        modelAndView.addObject("user", user);
        return modelAndView;
    }
//@RequestMapping(value = "/takeExam" ,method = RequestMethod.POST)
//public ModelAndView takeExam(@ModelAttribute("exam")ExamDto examDto){
//    Exam exam = examService.getExamById(examDto.getId());
//}

}

