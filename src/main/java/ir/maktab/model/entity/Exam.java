
package ir.maktab.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ir.maktab.util.CustomKeyDeserializer;
import ir.maktab.util.CustomKeySerializer;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;


    private Date startDate;

    private Date endDate;

    private Integer time;

    @ManyToMany
    private List<Student> studentList;

    @JsonIgnore
    @OneToMany(mappedBy = "exam")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Question> questions;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    private Boolean status;

    @ElementCollection
    @MapKeyJoinColumn(name = "question_id")
    @Column(name = "question_score")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonDeserialize(keyUsing = CustomKeyDeserializer.class)
    @JsonSerialize(keyUsing = CustomKeySerializer.class)
    private Map<Question, Double> scoreEachQuestion;

    public Exam() {
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


    public Boolean getExamState() {
        return status;
    }

    public void setExamState(Boolean status) {
        this.status = status;
    }

    public Map<Question, Double> getScoreEachQuestion() {
        return scoreEachQuestion;
    }

    public void setScoreEachQuestion(Map<Question, Double> scoreEachQuestion) {
        this.scoreEachQuestion = scoreEachQuestion;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
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
                ", status=" + status +
                '}';
    }


}

