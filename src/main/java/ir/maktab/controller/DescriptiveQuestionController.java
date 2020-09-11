package ir.maktab.controller;

import ir.maktab.model.dto.QuestionDto;
import ir.maktab.model.entity.DescriptiveQuestion;
import ir.maktab.model.entity.Exam;
import ir.maktab.model.entity.User;
import ir.maktab.service.ClassificationService;
import ir.maktab.service.DescriptiveQuestionService;
import ir.maktab.service.ExamService;
import ir.maktab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DescriptiveQuestionController {

    private DescriptiveQuestionService dQuestionService;
    private ClassificationService classificationService;
    private UserService userService;
    private ExamService examService;

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
                                           @RequestParam("teacher") String teacherId) {
        Exam exam = examService.getExamById(Long.valueOf(examId));
        User teacher = userService.findById(Long.valueOf(teacherId));
        ModelAndView modelAndView = new ModelAndView("teacher_descriptive");
        modelAndView.addObject("exam", exam);
        modelAndView.addObject("teacher", teacher);
        modelAndView.addObject("question",new QuestionDto());
        return modelAndView;
    }


    @RequestMapping(value = "newDescriptiveQuestion", method = RequestMethod.GET)
    public ModelAndView newDescriptiveQuestion(@ModelAttribute("question") QuestionDto questionDto
            , @RequestParam("examId") String examId
            , @RequestParam("status") String status
            , @RequestParam("score") String score) {
        DescriptiveQuestion dQuestion = dQuestionService.createDQuestion(questionDto, examId);
        Double totalScore = examService.addExamScore(Long.valueOf((examId)), Double.valueOf(score), dQuestion);
        if (status.equals("YES")) {
            classificationService.addQuestionToClassification(dQuestion);
        }
        ModelAndView modelAndView = new ModelAndView("simpleMessage", "message", "descriptive question added. Total Score " +
                "of exam is : " + totalScore);

        return modelAndView;
    }

}
