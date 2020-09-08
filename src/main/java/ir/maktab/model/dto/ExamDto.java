package ir.maktab.model.dto;

import ir.maktab.model.entity.Course;
import ir.maktab.model.entity.Question;
import ir.maktab.model.entity.Student;
import ir.maktab.model.entity.Teacher;
import ir.maktab.util.ExamStatus;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExamDto {

    private Integer id;

    private String title;

    private String description;

    private Date startDate;

    private Date endDate;

    private ExamStatus examState;

    private Integer time;

    private int score;

    @JsonIgnore
    private List<Question> questions;

    private Teacher teacher;

    private Course course;


    private List<Student> examiners;

//    private Map<Question, Integer> scoreEachQuestion = new HashMap<>();

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

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
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

    public ExamStatus getExamState() {
        return examState;
    }

    public void setExamState(ExamStatus examState) {
        this.examState = examState;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

//    public Map<Question, Integer> getScoreEachQuestion() {
//        return scoreEachQuestion;
//    }
//
//    public void setScoreEachQuestion(Map<Question, Integer> scoreEachQuestion) {
//        this.scoreEachQuestion = scoreEachQuestion;
//    }
}

