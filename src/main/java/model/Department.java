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
    private Long deptno;
    @Column(name = "DEPARTMENT_NAME")
    private String dname;
    @Column(name = "MANAGER_ID")
    private Long mgrid;
    @Column(name = "LOCATION_ID")
    private Long locid;

}
