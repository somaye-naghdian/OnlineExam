
package ir.maktab.model.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;

@Entity
public class Quiz {
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

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Question> questions;


}

