
package ir.maktab.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ir.maktab.util.CustomKeyDeserializer;
import ir.maktab.util.CustomKeySerializer;
import ir.maktab.util.ExamStatus;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 2, max = 20, message = "The title must be between 5 and 20 messages.")
    @NotNull(message = "Please provide a title")
    private String title;

    @Size(max = 500, message = "The description can't be longer than 500 characters.")
    @NotNull(message = "Please provide a description")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date endDate;

    private Integer time;


    @JsonIgnore
    @OneToMany(mappedBy = "exam", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Question> questions;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;
    @ManyToMany
    private List<Student> examiners;

    private ExamStatus examState;

    @ElementCollection
//    @JsonIgnore
//    @JsonManagedReference
    @MapKeyJoinColumn(name = "question_id")
    @Column(name = "question_score")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonDeserialize(keyUsing = CustomKeyDeserializer.class)
    @JsonSerialize(keyUsing = CustomKeySerializer.class)
    private Map<Question, Double> scoreEachQuestion ;

    public Exam() {
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

    public Map<Question, Double> getScoreEachQuestion() {
        return scoreEachQuestion;
    }

    public void setScoreEachQuestion(Map<Question, Double> scoreEachQuestion) {
        this.scoreEachQuestion = scoreEachQuestion;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", time=" + time +
                ", examState=" + examState +
                '}';
    }
}

