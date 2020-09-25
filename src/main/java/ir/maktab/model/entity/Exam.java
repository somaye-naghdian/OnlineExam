
package ir.maktab.model.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Student> studentList;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    private Boolean status;

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @MapKeyJoinColumn(name = "question_id")
    @Column(name = "question_score")
    private Map<Question, Double> scoreEachQuestion;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyJoinColumn(name = "student_id")
    @Column(name = "start_time")
    private Map<Student, java.util.Date> studentsStartTimes;

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Map<Student, java.util.Date> getStudentsStartTimes() {
        return studentsStartTimes;
    }

    public void setStudentsStartTimes(Map<Student, java.util.Date> students) {
        this.studentsStartTimes = students;
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

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return Objects.equals(id, exam.id) &&
                Objects.equals(title, exam.title) &&
                Objects.equals(description, exam.description) &&
                Objects.equals(startDate, exam.startDate) &&
                Objects.equals(endDate, exam.endDate) &&
                Objects.equals(time, exam.time) &&
                Objects.equals(studentList, exam.studentList) &&
                Objects.equals(teacher, exam.teacher) &&
                Objects.equals(course, exam.course) &&
                Objects.equals(status, exam.status) &&
                Objects.equals(scoreEachQuestion, exam.scoreEachQuestion) &&
                Objects.equals(studentsStartTimes, exam.studentsStartTimes);
    }

}

