package ir.maktab.model.dto;

import ir.maktab.model.entity.Question;

import java.util.List;

public class ClassificationDto {

    private Integer id;

    private String classificationTitle;

    private List<Question> questionBank;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
