package model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "Departments")
public class Departments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deptno;
    private String dname;
    private Integer mgrid;
    private Integer locid;

}
