package ir.maktab.model.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Classification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String classificationTitle;

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Question> questionBank;

    public Classification() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassificationTitle() {
        return classificationTitle;
    }

    public void setClassificationTitle(String classificationTitle) {
        this.classificationTitle = classificationTitle;
    }

    public List<Question> getQuestionBank() {
        return questionBank;
    }

    public void setQuestionBank(List<Question> questionBank) {
        this.questionBank = questionBank;
    }

    @Override
    public String toString() {
        return "Classification{" +
                "id=" + id +
                ", classificationTitle='" + classificationTitle + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classification that = (Classification) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(classificationTitle, that.classificationTitle) &&
                Objects.equals(questionBank, that.questionBank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, classificationTitle, questionBank);
    }
}
