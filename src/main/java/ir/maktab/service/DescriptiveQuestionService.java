package ir.maktab.service;

import ir.maktab.model.dto.QuestionDto;
import ir.maktab.model.entity.Classification;
import ir.maktab.model.entity.DescriptiveQuestion;
import ir.maktab.model.entity.Exam;
import ir.maktab.model.entity.Question;
import ir.maktab.model.repository.DescriptiveQuestionRepository;
import ir.maktab.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DescriptiveQuestionService {

    private DescriptiveQuestionRepository dQuestionRepository;
    private ExamService examService;
    private Mapper mapper;

    @Autowired
    public DescriptiveQuestionService(DescriptiveQuestionRepository dQuestionRepository
            , ExamService examService, Mapper mapper) {
        this.dQuestionRepository = dQuestionRepository;
        this.examService = examService;
        this.mapper = mapper;
    }


    public DescriptiveQuestion saveQuestion(DescriptiveQuestion descriptiveQuestion) {
        return dQuestionRepository.save(descriptiveQuestion);
    }


    @Modifying
    @Transactional
    public DescriptiveQuestion createDQuestion(QuestionDto questionDto, String examId) {
        Classification classification = examService.getExamById(Long.valueOf((examId))).getCourse().getClassification();
        Question question = mapper.convertDtoToQuestionEntity(questionDto);
        DescriptiveQuestion descriptiveQuestion = new DescriptiveQuestion(question);
        descriptiveQuestion.setExam(examService.getExamById(Long.valueOf((examId))));
        descriptiveQuestion.setClassification(classification);
        descriptiveQuestion.setType("descriptive");
        return dQuestionRepository.save(descriptiveQuestion);
    }


    public DescriptiveQuestion getDQuestionByID(Long id) {
        return dQuestionRepository.findById(id);
    }

    public List<DescriptiveQuestion> getAllDescriptiveQuestions() {
        return dQuestionRepository.findAll();
    }
}
