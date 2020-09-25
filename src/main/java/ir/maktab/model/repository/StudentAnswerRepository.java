package ir.maktab.model.repository;

import ir.maktab.model.entity.Exam;
import ir.maktab.model.entity.Question;
import ir.maktab.model.entity.Student;
import ir.maktab.model.entity.StudentAnswer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@org.springframework.stereotype.Repository
public interface StudentAnswerRepository extends Repository<StudentAnswer, Long> {

    StudentAnswer save(StudentAnswer studentAnswer);

    StudentAnswer findById(Long id);

    List<StudentAnswer> findAll();

    List<StudentAnswer> getStudentAnswerByExamAndStudent(Exam exam, Student student);

    StudentAnswer findByQuestion(Question question);

    List<StudentAnswer> findByExam(Exam exam);

    List<StudentAnswer> findByStudent(Student student);

    @Query("select s from StudentAnswer s where s.student.id=:studentId  ")
    List<StudentAnswer> getStudentAnswerByStudentId(@Param("studentId") Long studentId);

    @Query("select s from StudentAnswer  s where s.student.id=:studentId and s.exam.id=:examId")
    List<StudentAnswer> getStudentAnswers(@Param("studentId") Long studentId, @Param("examId") Long examId);

    @Modifying(clearAutomatically = true)
    @Query("update StudentAnswer s set s.answer=:newAnswer where s.question=:question")
    void updateStudentAnswer(@Param("question") Question question, @Param("newAnswer") String newAnswer);
}
