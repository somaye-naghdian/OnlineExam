package ir.maktab.model.dto;

import ir.maktab.model.entity.Course;
import java.util.List;

public class ClassificationDto {

    private Integer id;

    private String classificationTitle;


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


}
