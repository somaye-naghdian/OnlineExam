package ir.maktab.model.repository;

import ir.maktab.model.entity.Answer;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface AnswerRepository extends Repository<Answer,Integer> {

    Answer save(Answer answer);

   List<Answer> findAll();


}
