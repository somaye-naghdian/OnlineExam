package ir.maktab.model.repository;

import ir.maktab.model.entity.Exam;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface ExamRepository extends Repository<Exam, Long> {

    Exam save(Exam exam);

    List<Exam> findAll();

    Exam findById(Long id);

    void delete(Exam exam);

}
