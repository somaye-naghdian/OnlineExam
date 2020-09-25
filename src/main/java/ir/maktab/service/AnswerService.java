package ir.maktab.service;

import ir.maktab.model.entity.Answer;
import ir.maktab.model.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    public void save(Answer answer){
        answerRepository.save(answer);
    }
}
