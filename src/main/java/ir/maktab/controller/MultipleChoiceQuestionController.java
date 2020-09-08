package ir.maktab.controller;

import ir.maktab.model.dto.QuestionDto;
import ir.maktab.model.entity.Classification;
import ir.maktab.model.entity.MultipleChoiceQuestion;
import ir.maktab.model.entity.Question;
import ir.maktab.service.ClassificationService;
import ir.maktab.service.ExamService;
import ir.maktab.service.MultipleChoiceQuestionService;
import ir.maktab.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MultipleChoiceQuestionController {

    private MultipleChoiceQuestionService mcQuestionService;
    private ExamService examService;
    private ClassificationService classificationService;
    private Mapper mapper;

    @Autowired
    public MultipleChoiceQuestionController(MultipleChoiceQuestionService mcQuestionService
            , ExamService examService,ClassificationService classificationService ,Mapper mapper) {
        this.mcQuestionService = mcQuestionService;
        this.examService = examService;
        this.classificationService=classificationService;
        this.mapper = mapper;
    }

    @RequestMapping(value = "/newMultipleChoiceQuestion", method = RequestMethod.GET)
    public ModelAndView addMultipleChoiceQuestion(@ModelAttribute("question") QuestionDto questionDto,
                                                  @RequestParam("examId") String examId, @RequestParam("status") String status,
                                                  @RequestParam("score") String score,@RequestParam("correctAnswer") String correctAnswer,
                                                  @RequestParam("answer") String answers) {

        MultipleChoiceQuestion choiceQuestion = mcQuestionService.saveMultiQuestion(questionDto, examId, answers,correctAnswer);
        Double totalScore = examService.addExamScore(Integer.parseInt(examId), Double.valueOf(score), choiceQuestion);
        if (status.equals("YES")) {
            classificationService.addMultiQuestionToClassification(choiceQuestion);
        }
        ModelAndView modelAndView = new ModelAndView("simpleMessage");
        return modelAndView.addObject("message", "multiple Choice Question added successfully and total Score " +
                "of exam is : " +totalScore );
    }
}



