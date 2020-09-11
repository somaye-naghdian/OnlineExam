package ir.maktab.model.repository;

import ir.maktab.model.entity.Classification;
import ir.maktab.model.entity.Question;
import ir.maktab.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Queue;

@org.springframework.stereotype.Repository
public interface ClassificationRepository extends Repository<Classification, Long> {

    Classification save(Classification classification);

    List<Classification> findAll();

   Classification findByClassificationTitle(String title);

   List<Question> findById(Long id);

    @Query("select questionBank from Classification where classificationTitle=:title")
    List<Question> findQuestionsById(@Param("title") String title);

}
