package ir.maktab.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class StudentAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Student student;

    @OneToOne
    private Exam exam;

    @OneToOne
    private Question question;


    private String answer;

    private Double score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentAnswer studentAnswer = (StudentAnswer) o;
        return Objects.equals(student, studentAnswer.student) &&
                Objects.equals(exam, studentAnswer.exam) &&
                Objects.equals(question, studentAnswer.question) &&
                Objects.equals(score, studentAnswer.score) ;
    }

}
