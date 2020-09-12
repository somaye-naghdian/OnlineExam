package ir.maktab.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;


@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String text;
  //  cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    @ManyToOne
    @JsonManagedReference
    @JsonIgnore
    private Exam exam;
    //cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    @ManyToOne
    private Classification classification;

    private String Type;

    public Question() {
    }

    public Question(String title, String text,Exam exam,Classification classification) {
        this.title=title;
        this.text=text;
        this.exam=exam;
        this.classification=classification;
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

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
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

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
