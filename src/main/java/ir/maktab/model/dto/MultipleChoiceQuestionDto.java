package ir.maktab.model.dto;

import ir.maktab.model.entity.Answer;
import ir.maktab.model.entity.Question;

import javax.persistence.*;
import java.util.List;

public class MultipleChoiceQuestionDto extends Question {

    private List<Answer> answers;

    private String correctAnswer;


    public MultipleChoiceQuestionDto() {
    }

    public MultipleChoiceQuestionDto(Question question) {
        super(question.getTitle(), question.getText());

    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return "MultipleChoiceQuestion{" +
                "correctAnswer=" + correctAnswer +
                '}';
    }
}
