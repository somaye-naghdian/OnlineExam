package ir.maktab.controller;

import ir.maktab.model.dto.QuestionDto;
import ir.maktab.model.dto.UserDto;
import ir.maktab.model.entity.DescriptiveQuestion;
import ir.maktab.model.entity.Exam;
import ir.maktab.model.entity.User;
import ir.maktab.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class DescriptiveQuestionController {

    private DescriptiveQuestionService dQuestionService;
    private ClassificationService classificationService;
    private UserService userService;
    private ExamService examService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentAnswerService studentAnswerService;

    @Autowired
    public DescriptiveQuestionController(DescriptiveQuestionService dQuestionService
            , ExamService examService, ClassificationService classificationService
            , UserService userService) {
        this.dQuestionService = dQuestionService;
        this.examService = examService;
        this.userService = userService;
        this.classificationService = classificationService;
    }

    @RequestMapping(value = "/descriptive", method = RequestMethod.GET)
    public ModelAndView getDescriptivePage(@RequestParam("examId") String examId,
                                           @RequestParam("teacher") String teacherId,
                                           HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        UserDto userDto = (UserDto) session.getAttribute("user");
        Exam exam = examService.getExamById(Long.valueOf(examId));
        User teacher = userService.findById(userDto.getId());
        ModelAndView modelAndView = new ModelAndView("teacher_descriptive");
        modelAndView.addObject("exam", exam);
        modelAndView.addObject("teacher", teacher);
        modelAndView.addObject("question", new QuestionDto());
        return modelAndView;
    }


    @RequestMapping(value = "newDescriptiveQuestion", method = RequestMethod.GET)
    public ModelAndView newDescriptiveQuestion(@ModelAttribute("question") QuestionDto questionDto
            , @RequestParam("examId") String examId
            , @RequestParam("status") String status
            , @RequestParam("score") String score) {
        DescriptiveQuestion dQuestion = dQuestionService.createDQuestion(questionDto, Long.valueOf(examId));
        Double totalScore = examService.addExamScore(Long.valueOf((examId)), Double.valueOf(score), dQuestion);
      //  examService.addQuestionToExam(Long.valueOf(examId),dQuestion);

        if (status.equals("YES")) {
            classificationService.addQuestionToClassification(dQuestion);
        }
        ModelAndView modelAndView = new ModelAndView("simpleMessage", "message", "descriptive question added. Total Score " +
                "of exam is : " + totalScore);

        return modelAndView;
    }
    @RequestMapping(value = "/getDescriptiveAnswer",method = RequestMethod.GET)
    public ModelAndView getStudentDescriptiveAnswers(@RequestParam("studentId") String studentId,
                                                     @RequestParam("examId") String examId) {
        Map<String, Double> studentDescriptiveAnswer = studentAnswerService.getStudentDescriptiveAnswer(Long.valueOf(studentId), Long.valueOf(examId));
        ModelAndView modelAndView = new ModelAndView("teacher_correctAnswer");
        modelAndView.addObject("student", studentService.getStudentById(Long.valueOf(studentId)));
        modelAndView.addObject("exam", examService.getExamById(Long.valueOf(examId)));
        modelAndView.addObject("descriptiveAnswer", studentDescriptiveAnswer);
        return modelAndView;
    }

    @GetMapping(value = "/addDescriptiveScore")
    public ModelAndView addDescriptiveScore(@RequestParam("studentId") String studentId,
                                            @RequestParam("examId") String examId,
                                            @RequestParam("score")String score ,
                                            HttpServletRequest request){
        HttpSession session = request.getSession(false);
        UserDto userDto = (UserDto) session.getAttribute("user");
        ModelAndView modelAndView =new ModelAndView("simpleMessage");
        try {
            if(studentAnswerService.addDescriptiveScore(Long.valueOf(studentId),Long.valueOf(examId),Double.parseDouble(score))) {
                modelAndView.addObject("message","score successfully submit");
            }
        } catch (Exception e) {
            modelAndView.addObject("message",e.getMessage());
        }
        return modelAndView;
    }
}
