package ir.maktab.model.entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 2, max = 10, message = "The question Title should be between 2 and 10 characters")
    private String title;

    @Size(min = 10, max = 150, message = "The question should be between 10 and 150 characters")
    private String text;

    @OneToMany
    private List<Answer> answers;

    @ManyToOne
    private Quiz quiz;

    @OneToOne
    private Answer correctAnswer;

    public Question() {
    }
}
