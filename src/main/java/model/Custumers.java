package model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
public class Custumers {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "userid")
    private String userid;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "age")
    private String age;
    @Basic
    @Column(name = "grade")
    private String grade;
    @Basic
    @Column(name = "job")
    private String job;
    @Basic
    @Column(name = "milege")
    private Double milege;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Custumers custumers = (Custumers) o;
        return Objects.equals(userid, custumers.userid) && Objects.equals(name, custumers.name) && Objects.equals(age, custumers.age) && Objects.equals(grade, custumers.grade) && Objects.equals(job, custumers.job) && Objects.equals(milege, custumers.milege);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, name, age, grade, job, milege);
    }
}
