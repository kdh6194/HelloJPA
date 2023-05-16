package jpa;


import model.Employee;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class HelloJPA04 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            // Criteria 사용준비
            CriteriaBuilder cb = em.getCriteriaBuilder();

            // 사원 데이터 조회
            CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
            Root<Employee> eq = query.from(Employee.class); //조회 대상 지정

            CriteriaQuery<Employee> cq = query.select(eq);
//            //List<Employee> emps = em.createQuery(cq).getResultList();
//            List<Employee> emps = em.createQuery(cq.select(eq)).getResultList();
//
//            for(Employee emp : emps) {
//                System.out.println(emp);
//            }
            // 사원 데이터 조회 - 이름, 부서번호, 입사일 : multiselect
            // 컬럼 지정 : 객체.get(변수명)
//            CriteriaQuery<Object[]> mcq = cb.createQuery(Object[].class);
//            Root<Employee> me = mcq.from(Employee.class);
//
//            mcq.multiselect(me.get("fname"),me.get("deptno"),me.get("hdate"));
//            List<Object[]> items = em.createQuery(mcq).getResultList();
//
//            for(Object[] item : items) {
//                System.out.println(item[0] + "/" + item[1] + "/" + item[2]);
//            }

            // 정렬 조회 : 부서번호 기준, orderby
//            Order deptid = cb.desc(eq.get("deptno"));
//            cq = query.select(eq).orderBy(deptid);
//            emps = em.createQuery(cq).getResultList();
//
//            for(Employee emp : emps) {
//                System.out.println(emp);
//            }

            // 조건 검색 : 직책인 IT_PROG인 사원 조회, where
//            Predicate jobid = cb.equal(eq.get("jobid"),"IT_PROG");
//            cq = query.select(eq).where(jobid);
//            emps = em.createQuery(cq).getResultList();
//
//            for(Employee emp : emps) {
//                System.out.println(emp);
//            }

            // 조건검색 : 연봉이 20000 이상인 사원 조회
//            Predicate salGE = cb.ge(eq.get("sal"),20000);
//            cq = query.select(eq).where(salGE);
//            List<Employee> emps = em.createQuery(cq).getResultList();
//
//            for(Employee emp : emps) {
//                System.out.println(emp);
//            }

            // 직책 수 조회1 -> 전체 갯수 출력
//            Expression cntJob = cb.count(eq.get("jobid"));
//            cq = query.select(cntJob);
//            List<Employee> cnt = em.createQuery(cq).getResultList();
//
//            System.out.println(cnt);


            // 직책 수 조회2 : distinct -> 해당 값들 출력
//            cq = query.select(eq.get("jobid")).distinct(true);
//            List<Employee> cnt = em.createQuery(cq).getResultList();
//
//            System.out.println(cnt);

            // 직책 수 조회3 : countDistinct -> 중복 제외한 남은 갯수
            Expression cntJob = cb.countDistinct(eq.get("jobid"));
            cq = query.select(cntJob);
            List<Employee> cnt = em.createQuery(cq).getResultList();

            System.out.println(cnt);

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
