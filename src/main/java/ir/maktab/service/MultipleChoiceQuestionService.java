package ir.maktab.service;

import ir.maktab.model.entity.MultipleChoiceQuestion;
import ir.maktab.model.repository.MultipleChoiceQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MultipleChoiceQuestionService {

    private MultipleChoiceQuestionRepository mcQuestionRepository;

    @Autowired
    public MultipleChoiceQuestionService(MultipleChoiceQuestionRepository mcQuestionRepository) {
        this.mcQuestionRepository = mcQuestionRepository;
    }

    @Transactional
    public MultipleChoiceQuestion save(MultipleChoiceQuestion multipleChoiceQuestion) {
        return mcQuestionRepository.save(multipleChoiceQuestion);
    }
}
