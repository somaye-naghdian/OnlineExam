package ir.maktab.service;

import ir.maktab.model.dto.QuestionDto;
import ir.maktab.model.entity.Answer;
import ir.maktab.model.entity.Classification;
import ir.maktab.model.entity.MultipleChoiceQuestion;
import ir.maktab.model.entity.Question;
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
    public MultipleChoiceQuestionService(MultipleChoiceQuestionRepository mcQuestionRepository
            , ExamService examService, Mapper mapper) {
        this.mcQuestionRepository = mcQuestionRepository;
        this.examService = examService;
        this.mapper = mapper;
    }

    @Transactional
    public MultipleChoiceQuestion saveMultiQuestion(QuestionDto questionDto, String examId, String answers,String correctAnswer) {
        Classification classification = examService.getExamById(Integer.parseInt(examId)).getCourse().getClassification();
        Question question = mapper.convertDtoToQuestionEntity(questionDto);
        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion(question);
        multipleChoiceQuestion.setExam(examService.getExamById(Integer.parseInt(examId)));
        multipleChoiceQuestion.setClassification(classification);
        multipleChoiceQuestion.setAnswers(getAnswerList(answers,multipleChoiceQuestion));
        Answer rightAnswer=new Answer();
        rightAnswer.setContent(correctAnswer);
        multipleChoiceQuestion.setCorrectAnswer(rightAnswer);
        return mcQuestionRepository.save(multipleChoiceQuestion);
    }

    public MultipleChoiceQuestion saveQuestion(MultipleChoiceQuestion multipleChoiceQuestion) {
        return mcQuestionRepository.save(multipleChoiceQuestion);
    }

    public List<Answer> getAnswerList(String answers,MultipleChoiceQuestion multipleChoiceQuestion) {
        String[] splitAnswer = answers.split(",");
        List<Answer> answerList = new ArrayList<>();
        for (int i = 0; i < splitAnswer.length; i++) {
            Answer answer = new Answer();
            answer.setContent(splitAnswer[i]);
            answer.setMultipleChoiceQuestion(multipleChoiceQuestion);

            answerList.add(answer);
        }
        return answerList;
    }
}
