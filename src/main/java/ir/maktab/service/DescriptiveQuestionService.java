package ir.maktab.service;

import ir.maktab.model.dto.QuestionDto;
import ir.maktab.model.entity.Classification;
import ir.maktab.model.entity.DescriptiveQuestion;
import ir.maktab.model.entity.Exam;
import ir.maktab.model.entity.Question;
import ir.maktab.model.repository.DescriptiveQuestionRepository;
import ir.maktab.model.repository.ExamRepository;
import ir.maktab.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DescriptiveQuestionService {
    @Autowired
    private DescriptiveQuestionRepository dQuestionRepository;
    @Autowired
    private ExamService examService;
    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private Mapper mapper;

    @Transactional
    public DescriptiveQuestion saveQuestion(DescriptiveQuestion descriptiveQuestion) {
        return dQuestionRepository.save(descriptiveQuestion);
    }


    @Modifying
    @Transactional
    public DescriptiveQuestion createDQuestion(QuestionDto questionDto, Long examId) {
        Classification classification = examService.getExamById(Long.valueOf((examId))).getCourse().getClassification();
        Question question = mapper.convertDtoToQuestionEntity(questionDto);
        DescriptiveQuestion descriptiveQuestion = new DescriptiveQuestion(question);
        descriptiveQuestion.setClassification(classification);
        descriptiveQuestion.setType("descriptive");
        return dQuestionRepository.save(descriptiveQuestion);
    }


    public DescriptiveQuestion getDQuestionByID(Long id) {
        return dQuestionRepository.findById(id);
    }

}
