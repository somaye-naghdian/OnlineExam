package ir.maktab.service;

import ir.maktab.exceptions.CourseAlreadyExist;
import ir.maktab.model.dto.QuestionDto;
import ir.maktab.model.entity.DescriptiveQuestion;
import ir.maktab.model.entity.Exam;
import ir.maktab.model.entity.Question;
import ir.maktab.model.repository.ClassificationRepository;
import ir.maktab.model.entity.Classification;
import ir.maktab.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ClassificationService {

    private ClassificationRepository classificationRepository;
    private ExamService examService;
    private QuestionService questionService;
    private DescriptiveQuestionService dqService;
    private Mapper mapper;

    @Autowired
    public ClassificationService(ClassificationRepository classificationRepository
    ,ExamService examService,QuestionService questionService,
                                 DescriptiveQuestionService dqService,Mapper mapper) {
        this.classificationRepository = classificationRepository;
        this.questionService=questionService;
        this.examService=examService;
        this.dqService=dqService;
        this.mapper=mapper;
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
    public Classification addQuestionToClassification(DescriptiveQuestion descriptiveQuestion){
        //System.out.println(questionDto.getId());
      //  Exam exam = examService.getExamById(Integer.parseInt(examId));
       // DescriptiveQuestion descriptiveQuestion = dqService.getDQuestionByID(questionDto.getId());
     //   Classification classification = exam.getCourse().getClassification();
     //   dqService.saveQuestion(descriptiveQuestion);
        Classification classification = descriptiveQuestion.getClassification();
        dqService.saveQuestion(descriptiveQuestion);
        classification.getQuestionBank().add(descriptiveQuestion);

       return classificationRepository.save(classification);
    }

}
