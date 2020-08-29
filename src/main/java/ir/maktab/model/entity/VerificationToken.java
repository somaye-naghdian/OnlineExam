package ir.maktab.model.entity;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE,orphanRemoval = true)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private Date expiryDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    private Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.DAY_OF_MONTH,1);
        return new Date(cal.getTime().getTime());
    }

    public VerificationToken(User user) {
    this.user=user;
    createDate=new Date();
    token= UUID.randomUUID().toString();
    expiryDate=calculateExpiryDate();
    }


    public VerificationToken() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
//
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


}