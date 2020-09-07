package ir.maktab.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class MultipleChoiceQuestion extends Question {

    @OneToMany(mappedBy = "multipleChoiceQuestion", fetch = FetchType.EAGER
    ,cascade = CascadeType.ALL)
    private List<Answer> answers;

    @OneToOne(cascade = CascadeType.ALL)
    private Answer correctAnswer;

    public MultipleChoiceQuestion() {
    }

    public MultipleChoiceQuestion(Question question) {
        super(question.getTitle(), question.getText(), question.getExam(), question.getClassification());

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
}
