package ir.maktab.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class MultipleChoiceQuestion extends Question {

    @OneToMany(mappedBy = "multipleChoiceQuestion", fetch = FetchType.EAGER)
    private List<Answer> answers;
    @OneToOne
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
}
