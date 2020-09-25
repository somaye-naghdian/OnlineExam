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

    @Autowired
    private ExamRepository examRepository;
    private MultipleChoiceQuestionRepository mcQuestionRepository;
    private ExamService examService;
    private QuestionService questionService;
    private Mapper mapper;
    @Autowired
    private AnswerService answerService;

    @Autowired
    public MultipleChoiceQuestionService(MultipleChoiceQuestionRepository mcQuestionRepository
            , ExamService examService, Mapper mapper, QuestionService questionService) {
        this.mcQuestionRepository = mcQuestionRepository;
        this.examService = examService;
        this.questionService = questionService;
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

    public List<MultipleChoiceQuestion> getAllMultiChoiceQuestion() {
        return mcQuestionRepository.findAll();
    }
//
//    public Double getScoreEachQuestion(String questionId, String answer) {
//        Question question = questionService.getQuestionById(Long.valueOf(questionId));
//
////    }
//
//
//    public Double getUserScore(Map<Question, String> answers, Exam exam) {
//        System.out.println(answers);
//        Double testTotalScore = null;
//        Map<Question, Double> scoreEachQuestion = exam.getScoreEachQuestion();
//        for (Question question :
//                answers.keySet()) {
//            if (question.getType().equals("multipleChoice")) {
//
//                String answer = answers.get(question);
//                MultipleChoiceQuestion multiQuestion = getMultiQuestionById(question.getId());
//                List<Answer> answers1 = multiQuestion.getAnswers();
////                if ()
//            }
//        }
//        return testTotalScore;
//    }


}
