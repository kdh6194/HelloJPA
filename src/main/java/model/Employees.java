package model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "Employees")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empno;
    private String fname;
    private String lname;
    private String email;
    private String phone;
//    private String hdate;
    private Date hdate;
    private String jobid;
    private Integer sal;
    private Double comm;
    private Integer mgrid;
    private Integer deptno;


    // persist 호출전에 regdate 컬럼에 현재 날짜/시간 대입
//@PrePersist
//    protected void onCreate() {
//    hdate = new Date();
//    }
}
