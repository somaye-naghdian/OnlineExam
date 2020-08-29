
package ir.maktab.model.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private User teacher;

    private String title;

    private String description;

    private Calendar startDate;

    private Calendar endDate;

    private Time timer;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Question> questions;


}

