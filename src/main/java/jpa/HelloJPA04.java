package jpa;


import model.Department;
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
//            Root<Employee> eq = query.from(Employee.class); //조회 대상 지정

//            CriteriaQuery<Employee> cq = query.select(eq);
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
//            Expression cntJob = cb.countDistinct(eq.get("jobid"));
//            cq = query.select(cntJob);
//            List<Employee> cnt = em.createQuery(cq).getResultList();
//
//            System.out.println(cnt);

            // 그룹핑 : 직책별 최대, 최소, 평균 급여, 직책 수 조회
            // 서로 다른 종류의 데이터 타입을 받아오기 위해서 사용
//            CriteriaBuilder gcb = em.getCriteriaBuilder();
//            CriteriaQuery<Object[]> gcq = gcb.createQuery(Object[].class);
//            Root<Employee> ge = gcq.from(Employee.class);
//
//            Expression maxSal = cb.max(ge.get("sal"));
//            Expression minSal = cb.min(ge.get("sal"));
//            Expression avgSal = cb.avg(ge.get("sal"));
//            Expression cntSal = cb.count(ge.get("sal"));
//
//            gcq.multiselect(ge.get("jobid"),maxSal,minSal,avgSal,cntSal);
//            gcq.groupBy(ge.get("jobid"));
//            List<Object[]> itmes = em.createQuery(gcq).getResultList();
//            for (Object[] item : itmes ) {
//                System.out.println(item[0] + "/" + item[1] + "/" + item[2] + "/" + item[3] + "/" + item[4]);
//            }

            // 서브쿼리1 : 평균연봉보다 작게 받는 사원들 조회
            //      -> 계속적인 에러 발생으로 작성 중단한 코드
//            CriteriaBuilder ecb = em.getCriteriaBuilder();
//            CriteriaQuery<Employee> ecq = ecb.createQuery(Employee.class);
//              Root<Employee> e = ecq.from(Employee.class);
//
            // 하위 쿼리
//            Subquery<Double> qryAsal = query.subquery(Double.class);
//            Root<Employee> s = ecq.from(Employee.class);
//            qryAsal.select( ecb.avg(s.get("sal")) );
//
            // 주 쿼리
//            Root<Employee> ee = ecq.from(Employee.class);
//            ecq.select(ee).where(ecb.lt(ee.get("sal"),qryAsal));
//            List<Employee> emps = em.createQuery(ecq).getResultList();
//
//            for(Employee emp : emps ) {
//                System.out.println(emp);
//            }


            // 서브쿼리2 : 부서번호가 60번인 사원들의 이름, 직책, 부서명 조회


            // join : 부서번호가 60번인 사원들의 이름, 직책, 부서명 조회
//            CriteriaQuery<Object[]> jcq = cb.createQuery(Object[].class);
//            Root<Employee> e = jcq.from(Employee.class);
//
//            // 2개의 객체를 조인함
//            Join<Employee, Department> jntb = e.join("department", JoinType.INNER);
//
//            // 조인 결과에서 원하는 컬럼 추출하는 질의문 작성
//            CriteriaQuery<Object[]> jnq = jcq.multiselect(e.get("fname"),e.get("jobid"),jntb.get("dname")).where(cb.equal( e.get("deptno"),60 ));
//
//            List<Object[]> items = em.createQuery(jnq).getResultList();
//
//            for(Object[] item : items ) {
//                System.out.println(item[0] + "/" + item[1] + "/" + item[2] );
//            }

            // 동적 쿼리
            // 직책이 IT_PROG인 사원 조회
            // 연봉이 10000이상인 사원 조회
            // 직책이 IT_PROG이고 연봉이 6000 이상인 사원 조회
            String fname = null;
            String jobid = "IT_PROG";
            Integer sal = 6000;

            cb = em.getCriteriaBuilder();
            query = cb.createQuery(Employee.class);
            Root<Employee> e = query.from(Employee.class);

            List<Predicate> predicates = new ArrayList<>(); // 조건절 저장 변수

            if(fname != null) {
                // cndtns.add("fname like concat('%', :fname, '%')");
                predicates.add(cb.like(e.get("fname"), "%" + fname + "%"));
            }

            if(jobid != null) {
                // cndtns.add(" jobid = :jobid ");
                predicates.add(cb.equal(e.get("jobid"), jobid));
            }

            if(sal != null) {
                // cndtns.add(" sal >= :sal ");
                predicates.add(cb.ge(e.get("sal"), sal));
            }

            query.where(predicates.toArray(new Predicate[0]));
            // query.where(cb.or(predicates.toArray(new Predicate[0])));

            List<Employee> emps = em.createQuery(query).getResultList();

            for(Employee emp : emps ) {
               System.out.println(emp);
            }

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
