package ir.maktab.controller;

import ir.maktab.model.dto.UserDto;
import ir.maktab.model.entity.Answer;
import ir.maktab.model.entity.Exam;
import ir.maktab.model.entity.Student;
import ir.maktab.model.entity.StudentAnswer;
import ir.maktab.service.CourseService;
import ir.maktab.service.ExamService;
import ir.maktab.service.StudentAnswerService;
import ir.maktab.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {
    private CourseService courseService;
    private StudentService studentService;
    @Autowired
    private ExamService examService;
    @Autowired
    private StudentAnswerService studentAnswerService;

    @Autowired
    public StudentController(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @RequestMapping(value = "/addStudentToCourse", method = RequestMethod.GET)
    public ModelAndView addStudentToCourse(@RequestParam("email") String email
            , @RequestParam("course") String courseTitle) {

        courseService.addUserToCourse(courseTitle, email);
        ModelAndView modelAndView = new ModelAndView("simpleMessage");
        modelAndView.addObject("message", "student successfully added to  " + courseTitle);
        return modelAndView;
    }

    @RequestMapping(value = "/deleteStudentFromCourse", method = RequestMethod.GET)
    public ModelAndView deleteStudentFromCourse(@RequestParam("email") String email
            , @RequestParam("course") String courseTitle) {
        courseService.deleteStudentFromCourse(courseTitle, email);
        ModelAndView modelAndView = new ModelAndView("simpleMessage");
        modelAndView.addObject("message", "student successfully delete from  " + courseTitle);
        return modelAndView;
    }



   @GetMapping(value = "/getStudentResult")
    public ModelAndView getStudentResult(HttpServletRequest request,
                                         @RequestParam("courseTitle")String courseTitle) {

        ModelAndView modelAndView = new ModelAndView("student_result");
        HttpSession session = request.getSession(false);
        UserDto userDto = (UserDto) session.getAttribute("user");
        Map<Exam, Double> studentExamResult = studentAnswerService.studentExamResult(userDto.getId(), courseTitle);
        modelAndView.addObject("studentExamResult", studentExamResult);
        return modelAndView;
    }

}
