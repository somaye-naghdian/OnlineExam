package ir.maktab.model.repository;

import ir.maktab.model.entity.MultipleChoiceQuestion;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface MultipleChoiceQuestionRepository extends Repository<MultipleChoiceQuestion, Integer> {

    MultipleChoiceQuestion save(MultipleChoiceQuestion multipleChoiceQuestion);
}
