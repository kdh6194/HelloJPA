package model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Long empno;
    @Column(name = "FIRST_NAME")
    private String fname;
    @Column(name = "LAST_NAME")
    private String lname;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PHONE_NUMBER")
    private String phone;
    @Column(name = "HIRE_DATE")
    private Date hdate;
    @Column(name = "JOB_ID")
    private String jobid;
    @Column(name = "SALARY")
    private Integer sal;
    @Column(name = "COMMISSION_PCT", precision=5, scale=2)
    private Double comm;
    @Column(name = "MANAGER_ID")
    private Integer mgrid;
    @Column(name = "DEPARTMENT_ID")
    private Integer deptno;

    @ManyToOne // 테이블 연관 관계 = 다 : 1 -> 외래키 설정시작
    @JoinColumn(name = "department_id") // department 테이블의 id 컬럼과 조인
    private Department department;
//    @JoinColumn(name = "deptno", insertable = false,updatable = false)


    // persist 호출전에 regdate 컬럼에 현재 날짜/시간 대입
//@PrePersist
//    protected void onCreate() {
//    hdate = new Date();
//    }
}
