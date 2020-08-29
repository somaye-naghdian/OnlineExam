package ir.maktab.model.dto;

import ir.maktab.model.entity.Answer;
import ir.maktab.model.entity.Exam;

import javax.validation.constraints.Size;
import java.util.List;

public class QuestionDto {

        private Integer id;

        @Size(min = 2, max = 10, message = "The question Title should be between 2 and 10 characters")

        private String title;

        @Size(min = 10, max = 150, message = "The question should be between 10 and 150 characters")
        private String text;


        private List<Answer> answers;

        private Exam exam;


        private Answer correctAnswer;

        public QuestionDto() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
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

        public Exam getExam() {
            return exam;
        }

        public void setExam(Exam exam) {
            this.exam = exam;
        }

        public Answer getCorrectAnswer() {
            return correctAnswer;
        }

        public void setCorrectAnswer(Answer correctAnswer) {
            this.correctAnswer = correctAnswer;
        }
    }
