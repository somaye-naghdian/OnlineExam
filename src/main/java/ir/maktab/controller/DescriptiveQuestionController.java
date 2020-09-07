package ir.maktab.controller;

import ir.maktab.model.dto.QuestionDto;
import ir.maktab.model.entity.DescriptiveQuestion;
import ir.maktab.service.ClassificationService;
import ir.maktab.service.DescriptiveQuestionService;
import ir.maktab.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DescriptiveQuestionController {

    private DescriptiveQuestionService dQuestionService;
    private ClassificationService classificationService;
    private ExamService examService;

    @Autowired
    public DescriptiveQuestionController(DescriptiveQuestionService dQuestionService
            , ExamService examService, ClassificationService classificationService) {
        this.dQuestionService = dQuestionService;
        this.examService = examService;
        this.classificationService = classificationService;
    }

    @RequestMapping(value = "newDescriptiveQuestion", method = RequestMethod.GET)
    public ModelAndView newDescriptiveQuestion(@ModelAttribute("question") QuestionDto questionDto
            , @RequestParam("examId") String examId
            , @RequestParam("status") String status
            , @RequestParam("score") String score) {
        DescriptiveQuestion dQuestion = dQuestionService.createDQuestion(questionDto, examId);
        examService.addExamScore(Integer.parseInt(examId), Double.valueOf(score), dQuestion);
        if (status.equals("YES")) {
            classificationService.addQuestionToClassification(dQuestion);
        }
        ModelAndView modelAndView = new ModelAndView("simpleMessage", "message", "descriptive question added");
        return modelAndView;
    }

}
