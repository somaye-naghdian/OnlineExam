package ir.maktab.model.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
public class MultipleChoiceQuestion extends Question {

    @OneToMany(mappedBy = "multipleChoiceQuestion")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Answer> answers;

    @OneToOne(cascade = CascadeType.ALL)
    private Answer correctAnswer;


    public MultipleChoiceQuestion() {
    }

    public MultipleChoiceQuestion(Question question) {
        super(question.getTitle(), question.getText());

    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Answer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return "MultipleChoiceQuestion{" +
                "correctAnswer=" + correctAnswer +
                '}';
    }


}
