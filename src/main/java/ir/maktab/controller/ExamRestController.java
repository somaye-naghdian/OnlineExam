package ir.maktab.controller;

import ir.maktab.model.entity.Answer;
import ir.maktab.model.entity.Question;
import ir.maktab.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ExamRestController {
    @Autowired
    private QuestionService questionService;

    Map<Question, String> answers = new HashMap<>();

    @PostMapping(value = "studentAnswer/{examId}/{studentId}/{questionId}")
    public void getStudentAnswer(@RequestBody Answer answer,
                                 @PathVariable("examId")String examId,
                                 @PathVariable("studentId")String studentId,
                                 @PathVariable("questionId")String questionId){
        Question question = questionService.getQuestionById(Long.valueOf(questionId));
        answers.put(question,answer.getContent());

    }

}
