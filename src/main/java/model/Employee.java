package model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
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
    private Long sal;
    @Column(name = "COMMISSION_PCT", precision=5, scale=2)
    private Double comm;
    @Column(name = "MANAGER_ID")
    private Long mgrid;
    @Column(name = "DEPARTMENT_ID")
    private Long deptno;

//    @ManyToOne // 테이블 연관 관계 = 다 : 1 -> 외래키 설정시작
//    @JoinColumn(name = "department_id") // department 테이블의 id 컬럼과 조인
//      private Department department;
//    @JoinColumn(name = "deptno", insertable = false,updatable = false)

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Employee{");
        sb.append("empno=").append(empno);
        sb.append(", fname='").append(fname).append('\'');
        sb.append(", lname='").append(lname).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", hdate=").append(hdate);
        sb.append(", jobid='").append(jobid).append('\'');
        sb.append(", sal=").append(sal);
        sb.append(", comm=").append(comm);
        sb.append(", mgrid=").append(mgrid);
        sb.append(", deptno=").append(deptno);
//        sb.append(", department=").append(department);
        sb.append('}');
        return sb.toString();
    }
}
