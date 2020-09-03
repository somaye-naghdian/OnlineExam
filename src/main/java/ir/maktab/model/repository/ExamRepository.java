package ir.maktab.model.repository;

import ir.maktab.model.entity.Course;
import ir.maktab.model.entity.Exam;
import ir.maktab.model.entity.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import javax.jnlp.IntegrationService;
import java.util.List;

@org.springframework.stereotype.Repository
public interface ExamRepository extends Repository<Exam, Integer> {

    Exam save(Exam exam);

    List<Exam> findAll();

    Exam findByTitle(String title);

}
