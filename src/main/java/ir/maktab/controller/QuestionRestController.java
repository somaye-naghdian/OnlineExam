package ir.maktab.controller;

import ir.maktab.model.dto.ExamDto;
import ir.maktab.model.entity.Classification;
import ir.maktab.model.entity.Question;
import ir.maktab.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionRestController {
    private ClassificationService classificationService;

    @Autowired
    public QuestionRestController(ClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    @GetMapping(value = "questionsFromQuestionBank")
    public List<Question> getQuestionFromBank(@ModelAttribute("exam") ExamDto examDto) {
        Classification classification = examDto.getCourse().getClassification();
        return classificationService.getQuestionFromBank(classification.getClassificationTitle());
    }
}
