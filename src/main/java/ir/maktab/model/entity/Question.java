package ir.maktab.model.entity;

import javax.persistence.*;


@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String text;

//    private int score;

    /*@OneToMany(mappedBy = "question")
    private List<Answer> answers;*/

    @ManyToOne(cascade = CascadeType.ALL)
    private Exam exam;

    @ManyToOne
    private Classification classification;

    public Question() {
    }

    public Question(String title, String text,Exam exam,Classification classification) {
        this.title=title;
        this.text=text;
        this.exam=exam;
        this.classification=classification;
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

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
