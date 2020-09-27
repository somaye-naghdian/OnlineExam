package ir.maktab.service;

import ir.maktab.model.dto.MultipleChoiceQuestionDto;
import ir.maktab.model.entity.*;
import ir.maktab.model.repository.ExamRepository;
import ir.maktab.model.repository.MultipleChoiceQuestionRepository;
import ir.maktab.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MultipleChoiceQuestionService {

    private MultipleChoiceQuestionRepository mcQuestionRepository;
    private ExamService examService;
    private Mapper mapper;
    @Autowired
    private AnswerService answerService;

    @Autowired
    public MultipleChoiceQuestionService(MultipleChoiceQuestionRepository mcQuestionRepository
            , ExamService examService, Mapper mapper) {
        this.mcQuestionRepository = mcQuestionRepository;
        this.examService = examService;
        this.mapper = mapper;
    }

    @Transactional
    public MultipleChoiceQuestion saveMultiQuestion(MultipleChoiceQuestionDto question, Long examId, String answers, String correctAnswer) {
        Classification classification = examService.getExamById(Long.valueOf((examId))).getCourse().getClassification();
        MultipleChoiceQuestion multipleChoiceQuestion = mapper.convertMultiQuestionDtoToEntity(question);
        multipleChoiceQuestion.setClassification(classification);
        multipleChoiceQuestion.setAnswers(getAnswerList(answers, multipleChoiceQuestion));
        Answer rightAnswer = new Answer();
        rightAnswer.setContent(correctAnswer);
        multipleChoiceQuestion.setCorrectAnswer(rightAnswer);
        multipleChoiceQuestion.setType("multipleChoice");

        return mcQuestionRepository.save(multipleChoiceQuestion);
    }

    @Transactional
    public MultipleChoiceQuestion saveQuestion(MultipleChoiceQuestion multipleChoiceQuestion) {
        return mcQuestionRepository.save(multipleChoiceQuestion);
    }

    @Transactional
    public List<Answer> getAnswerList(String answers, MultipleChoiceQuestion multipleChoiceQuestion) {
        String[] splitAnswer = answers.split(",");
        List<Answer> answerList = new ArrayList<>();
        for (int i = 0; i < splitAnswer.length; i++) {
            Answer answer = new Answer();
            answer.setContent(splitAnswer[i]);
            answer.setMultipleChoiceQuestion(multipleChoiceQuestion);
            answerList.add(answer);
            answer.setMultipleChoiceQuestion(multipleChoiceQuestion);
            answerService.save(answer);
        }
        return answerList;
    }

    public MultipleChoiceQuestion getMultiQuestionById(Long id) {
        return mcQuestionRepository.findById(id);
    }

}
