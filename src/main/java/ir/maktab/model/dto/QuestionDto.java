package ir.maktab.model.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ir.maktab.model.entity.Answer;
import ir.maktab.model.entity.Classification;
import ir.maktab.model.entity.Exam;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;
import java.util.List;

public class QuestionDto {

    private Long id;

    @Size(min = 2, max = 10, message = "The question Title should be between 2 and 10 characters")

    private String title;

    @Size(min = 10, max = 150, message = "The question should be between 10 and 150 characters")
    private String text;

    private Answer answer;
    private List<Answer> answers;


    private List<Exam> exam;

    private Classification classification;

    private String Type;

    public QuestionDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Exam> getExam() {
        return exam;
    }

    public void setExam(List<Exam> exam) {
        this.exam = exam;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "QuestionDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }
}
