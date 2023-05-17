package dsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import model.Employee;
import model.QDepartment;
import model.QEmployee;
import org.apache.commons.lang.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

public class HelloQueryDSL03 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
           //CRUD 활용 -> Transaction 기반으로 작성되어야한다.
           // 사원 추가
//            tx.begin();
    //           Employee e = new Employee();
    //            e.setFname("jobs");
    //            e.setSal(15000L);
    //           em.persist(e);
//            tx.commit();

            // 조회는 HelloQueryDSL02에 작성되어 있으니 참고하면 됨

            // 사원 수정
            QEmployee qemp = QEmployee.employee;
            JPAQueryFactory query = new JPAQueryFactory(em);


//            tx.begin();
//                query.update(qemp)
//                .set(qemp.fname,"abc123")
//                .set(qemp.lname,"987xyz")
//                .set(qemp.sal,15500L)
//                .set(qemp.jobid,"Game_QC")
//                .where(qemp.empno.eq(998L))
//                .execute();
//            tx.commit();

            // 이렇게도 작성 가능
//            Employee employee = em.find(Employee.class, 1L);
//            employee.setSalary(6000);
//            em.merge(employee);

            // 사원데이터 삭제

//            tx.begin();
//            query.delete(qemp)
//                    .where(qemp.empno.eq(994L))
//                    .execute();
//            tx.commit();

            // 사원데이터 삭제 - 한번에 삭제
//            List<Long> empnoList = Arrays.asList(995L, 996L, 998L);
//
//            tx.begin();
//            query.delete(qemp)
//                    .where(qemp.empno.in(empnoList))
//                    .execute();
//            tx.commit();

        }catch (Exception ex){
            ex.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
