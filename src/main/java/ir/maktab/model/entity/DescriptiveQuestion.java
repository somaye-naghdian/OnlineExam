package ir.maktab.model.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class DescriptiveQuestion extends Question {

    @OneToOne
    private Answer answer;

    public DescriptiveQuestion(Question question) {
        super(question.getTitle(), question.getText(),question.getExam(),question.getClassification());

    }

    public DescriptiveQuestion() {
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
