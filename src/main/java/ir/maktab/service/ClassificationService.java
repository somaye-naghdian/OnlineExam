package ir.maktab.service;

import ir.maktab.exceptions.CourseAlreadyExist;
import ir.maktab.model.entity.*;
import ir.maktab.model.repository.ClassificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ClassificationService {

    private ClassificationRepository classificationRepository;
    private DescriptiveQuestionService dqService;
    private MultipleChoiceQuestionService mcqService;


    @Autowired
    public ClassificationService(ClassificationRepository classificationRepository
            , DescriptiveQuestionService dqService, MultipleChoiceQuestionService mcqService) {
        this.classificationRepository = classificationRepository;
        this.mcqService = mcqService;
        this.dqService = dqService;

    }

    @Transactional
    public boolean addClassification(Classification classification) {

        try {
            Classification title = classificationRepository.findByClassificationTitle(classification.getClassificationTitle());
            if (StringUtils.isEmpty(title) || title == null) {
                classificationRepository.save(classification);
                return true;
            }

        } catch (NullPointerException e) {
            throw new CourseAlreadyExist(" already exist a classification with this specification");
        }
        return false;
    }

    public List<Classification> getAllClassification() {
        return classificationRepository.findAll();
    }

    public Classification getClassificationByTitle(String title) {
        return classificationRepository.findByClassificationTitle(title);
    }

    public List<Question> getQuestionFromBank(String classificationTitle) {
        return classificationRepository.findQuestionsById(classificationTitle);
    }

    @Transactional
    public Classification addQuestionToClassification(DescriptiveQuestion descriptiveQuestion) {
        Classification classification = descriptiveQuestion.getClassification();
        dqService.saveQuestion(descriptiveQuestion);
        classification.getQuestionBank().add(descriptiveQuestion);

        return classificationRepository.save(classification);
    }


    @Transactional
    public Classification addMultiQuestionToClassification(MultipleChoiceQuestion multipleChoiceQuestion) {
        Classification classification = multipleChoiceQuestion.getClassification();
        Answer correctAnswer = multipleChoiceQuestion.getCorrectAnswer();
        List<Answer> answers = multipleChoiceQuestion.getAnswers();
        for (Answer answer : answers) {
            answer.setCorrect(false);
            if (answer.getContent().equals(correctAnswer)) {
                answer.setCorrect(true);
            }
        }
        mcqService.saveQuestion(multipleChoiceQuestion);
        classification.getQuestionBank().add(multipleChoiceQuestion);

        return classificationRepository.save(classification);
    }

}
