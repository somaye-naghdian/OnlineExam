package ir.maktab.service;

import ir.maktab.model.entity.Question;
import ir.maktab.model.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;
    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public QuestionService() {
    }
    @Transactional
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question getQuestionById(Long id){
      return   questionRepository.findById(id);
    }


    public Map<Integer, Question> getQuestionMap(List<Question> questions) {
        Map<Integer, Question> questionList = new HashMap<>();
        int i = 1;
        for (Question question :
                questions) {
            questionList.put(i, question);
            i++;
        }
        return questionList;
    }
}
