package ir.maktab.model.dto;

import ir.maktab.model.entity.Course;
import ir.maktab.model.entity.Question;
import ir.maktab.model.entity.Teacher;
import ir.maktab.util.ExamStatus;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;


public class ExamDto {

    private Long id;

    @Size(min = 2, max = 20, message = "The title must be between 5 and 20 messages.")
//    @ (pattern= "[a-zA-Z].{2,20}")
    @NotNull(message = "Please provide a title")
    private String title;

    @Size(max = 500, message = "The description can't be longer than 500 characters.")
    @NotNull(message = "Please provide a description")
    private String description;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String startDate;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String endDate;

    private ExamStatus examState;

    private Integer time;


    @JsonIgnore
    private List<Question> questions;

    private Teacher teacher;

    private Course course;


//    private Map<Question, Integer> scoreEachQuestion = new HashMap<>();

    public ExamDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
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

    public ExamStatus getExamState() {
        return examState;
    }

    public void setExamState(ExamStatus examState) {
        this.examState = examState;
    }


//    public Map<Question, Integer> getScoreEachQuestion() {
//        return scoreEachQuestion;
//    }
//
//    public void setScoreEachQuestion(Map<Question, Integer> scoreEachQuestion) {
//        this.scoreEachQuestion = scoreEachQuestion;
//    }


    @Override
    public String toString() {
        return "ExamDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", examState=" + examState +
                ", time=" + time +
                '}';
    }
}

