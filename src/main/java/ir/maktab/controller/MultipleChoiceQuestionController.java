package ir.maktab.controller;

import ir.maktab.model.dto.QuestionDto;
import ir.maktab.model.entity.Classification;
import ir.maktab.model.entity.MultipleChoiceQuestion;
import ir.maktab.model.entity.Question;
import ir.maktab.service.ExamService;
import ir.maktab.service.MultipleChoiceQuestionService;
import ir.maktab.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/multipleChoiceQuestion")
public class MultipleChoiceQuestionController {

    private MultipleChoiceQuestionService mcQuestionService;
    private ExamService examService;
    private Mapper mapper;

    @Autowired
    public MultipleChoiceQuestionController(MultipleChoiceQuestionService mcQuestionService
            , ExamService examService, Mapper mapper) {
        this.mcQuestionService = mcQuestionService;
        this.examService = examService;
        this.mapper = mapper;
    }

    @RequestMapping(value = "/addMultipleChoiceQuestion ")
    public ModelAndView addMultipleChoiceQuestion(@ModelAttribute("question") QuestionDto questionDto,
                                                  @RequestParam("examId") String examId) {
        System.out.println(questionDto);
        ModelAndView modelAndView = new ModelAndView("simpleMessage");
        Question question = mapper.convertDtoToQuestionEntity(questionDto);
        Classification classification = examService.getExamById(Integer.parseInt(examId)).getCourse().getClassification();
        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion(question);
        multipleChoiceQuestion.setExam(examService.getExamById(Integer.parseInt(examId)));
        multipleChoiceQuestion.setClassification(classification);
        try {
            mcQuestionService.save(multipleChoiceQuestion);
            return modelAndView.addObject("message", "multipleChoiceQuestion Question added successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return modelAndView.addObject("message", "error " + e.getMessage());
        }
    }
}



