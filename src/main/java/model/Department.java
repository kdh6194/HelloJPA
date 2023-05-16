package model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTMENT_ID")
    private Integer deptno;
    @Column(name = "DEPARTMENT_NAME")
    private String dname;
    @Column(name = "MANAGER_ID")
    private Integer mgrid;
    @Column(name = "LOCATION_ID")
    private Integer locid;

}
