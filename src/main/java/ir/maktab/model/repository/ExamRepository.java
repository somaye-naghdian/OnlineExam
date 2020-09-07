package ir.maktab.model.repository;

import ir.maktab.model.entity.Exam;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface ExamRepository extends Repository<Exam, Integer> {

    Exam save(Exam exam);

    List<Exam> findAll();

    Exam findByTitle(String title);

    Exam findById(Integer id);

    void deleteById(Integer id);

}
