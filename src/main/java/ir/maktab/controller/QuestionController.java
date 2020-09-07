package ir.maktab.controller;

import ir.maktab.model.dto.ExamDto;
import ir.maktab.model.dto.QuestionDto;
import ir.maktab.model.entity.*;
import ir.maktab.service.ClassificationService;
import ir.maktab.service.ExamService;
import ir.maktab.util.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class QuestionController {

    private ExamService examService;
    private ClassificationService classificationService;
    private Mapper mapper;

    public QuestionController(ExamService examService, Mapper mapper
    ,ClassificationService classificationService) {
        this.examService = examService;
        this.classificationService=classificationService;
        this.mapper = mapper;
    }

    @RequestMapping(value = "addQuestionToExam",method = RequestMethod.GET)
    public ModelAndView addQuestionToExam( @RequestParam("id") String examId) {
        Exam exam = examService.getExamById(Integer.parseInt(examId));
        ExamDto examDto = mapper.convertEntityToExamDto(exam);
        ModelAndView modelAndView = new ModelAndView("teacher_question2","exam",examDto);
       modelAndView.addObject("question",new QuestionDto());
       modelAndView.addObject("exam",examDto);
        return modelAndView;
    }


    @RequestMapping(value = "/getFromQuestionBank",method = RequestMethod.GET)
    public ModelAndView getQuestionBank(@RequestParam("examId")String examId){
        Exam exam = examService.getExamById(Integer.parseInt(examId));
        Classification classification = exam.getCourse().getClassification();
        List<Question> questionFromBank = classificationService.getQuestionFromBank(classification.getClassificationTitle());
        ModelAndView modelAndView =new ModelAndView("teacher_questionBank");
        modelAndView.addObject("questionFromBank",questionFromBank);
        return modelAndView;
    }


}
