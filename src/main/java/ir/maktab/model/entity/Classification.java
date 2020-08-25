package ir.maktab.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Classification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String classificationTitle;

    public Classification() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassificationTitle() {
        return classificationTitle;
    }

    public void setClassificationTitle(String classificationTitle) {
        this.classificationTitle = classificationTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classification that = (Classification) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(classificationTitle, that.classificationTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, classificationTitle);
    }

    @Override
    public String toString() {
        return "Classification{" +
                "id=" + id +
                ", classificationTitle='" + classificationTitle + '\'' +
                '}';
    }
}
