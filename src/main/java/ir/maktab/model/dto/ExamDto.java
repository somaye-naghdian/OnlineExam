package ir.maktab.model.dto;

import ir.maktab.model.entity.Course;
import ir.maktab.model.entity.Question;
import ir.maktab.model.entity.Student;
import ir.maktab.model.entity.Teacher;

import java.util.Date;
import java.util.List;


public class ExamDto {

    private Integer id;

    private String title;

    private String description;

    private Date startDate;

    private Date endDate;

    private Integer timer;

    private List<Question> questions;

    private Teacher teacher;


    private Course course;


    private List<Student> examiners;

    public ExamDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getTimer() {
        return timer;
    }

    public void setTimer(Integer timer) {
        this.timer = timer;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Student> getExaminers() {
        return examiners;
    }

    public void setExaminers(List<Student> examiners) {
        this.examiners = examiners;
    }
}

