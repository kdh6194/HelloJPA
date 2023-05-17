package jpa;


import model.Employee;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class HelloJPA03 {
    public static void main(String[] args) {
    // jpa 객체 초기화 : emf -> em -> tx
        // 보통 에러가 발생하는 이유는 persistence.xml에서 잘못 설정해서
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            // 데이터 추가 : persist(대상)
                //Employee emp = new Employee();

                //emp.setFname("steave");
                //emp.setLname("jobs");
                //emp.setEmail("abc123@naverc.com");
                //emp.setPhone("010.123.4567");
                //emp.setJobid("IT_PROG");
                //emp.setSal(3500);
                //emp.setMgrid(100);
                //emp.setDeptno(90);

                //tx.begin();
                    //em.persist(emp);
                //tx.commit();

            // 데이터 조회 : find(객체, 기본키 값)
                //tx.begin();
                    //emp = em.find(Employee.class,1);
                    //em.persist(emp);
                //tx.commit();
             //데이터 수정 : setXxx(변경값)
                //tx.begin();
                     //emp = em.find(Employee.class,2); // 수정할 객체 찾기
                     //emp.setFname("Oking"); //  값 변경
                     //em.persist(emp);
                //tx.commit();
             //데이터 삭제 : remove(대상)
                 //tx.begin();
                    //emp = em.find(Employee.class,3); // 삭제할 객체 찾기
                    //em.remove(emp);
                 //tx.commit();

    // JPQL
        // 사원 데이터 조회 - TypedQuery
            // createQuery(질의문, 리턴될 객체 종류)
            String jpql = "select e from Employee as e";
            //List<Employee> emps = em.createQuery(jpql,Employee.class).getResultList();

            //for (Employee emp: emps){System.out.println(emp);}
        // 사원 데이터 조회 - Query : 이름, 부서번호, 입사일
            // createQuery(질의문)
        jpql = "select fname, deptno, hdate from Employee e";
            //List<Object[]> items = em.createQuery(jpql).getResultList();

            //for(Object[] item : items) {System.out.println(item[0] + "/" + item[1] + "/" + item[2]);}
        // 사원 직책 조회 - 직책이 IT_PROG인 사원
            // 파라메타 바인딩 - :파라메타명, ?순번
            jpql = "select e from Employee e where jobid = :jobid "; // -> 이름 기반
//            jpql = "select e from Employee e where jobid = ?1 "; -> 위치 기반
            //TypedQuery<Employee> query = em.createQuery(jpql,Employee.class);
            //query.setParameter("jobid","IT_PROG"); // -> 이름 기반
//            query.setParameter(1,"IT_PROG"); // -> 숫자 기반
            //query.getResultList();

            //for (Employee emp: emps){System.out.println(emp);}

            // 사원 평균 연봉 조회 - TypedQuery
            // createQuery(질의문, 리턴될 객체 종류)
            jpql = "select avg(sal) from Employee e";
//            Double avgsal = em.createQuery(jpql, Double.class).getSingleResult();
//
//            System.out.println(avgsal);

            // 사원 직책수 조회
            jpql = "select count(jobid) from Employee e";
//            Long tot = em.createQuery(jpql, Long.class).getSingleResult();
//
//            System.out.println(tot);

            // jobid로 정렬 후 3페이지 조회 : 페이징 (페이지당 출력건수)
            // setFirstResult(startpos) : 페이징 시작 위치
            // setMaxResult(getdatacnt) : 조회할 데이터 수
            jpql = "select e from Employee e order by jobid";
//            List<Employee> pemps = em.createQuery(jpql, Employee.class).setFirstResult(30).setMaxResults(15).getResultList();
//
//            for (Employee emp: pemps){System.out.println(emp);}

            // 직책별 평균 연봉 조회
            jpql = "select jobid, avg(sal), count(jobid) from Employee e group by jobid";
//            List<Object[]> select = em.createQuery(jpql).getResultList();
//
//            for (Object[] emp: select){System.out.println(emp[0] +  "/" + emp[1] + "/" + emp[2]);}

            // 사원 이름, 직책, 부서명 조회 : join
            jpql = "select e.fname, e.jobid, e.deptno, d.dname from Employee e inner join e.department d";
//            List<Object[]> items = em.createQuery(jpql).getResultList();
//
//            for (Object[] item : items)
//                System.out.println(item[0] + "/" + item[1] + "/" + item[2] + "/" + item[3]);

            // 부서명이 IT인 사원의 사번과 입사일 조회 : 서브 쿼리
            jpql = "select empno, hdate from Employee e where deptno = (select deptno from Department d where dname = 'IT')";
//            List<Object[]> items1 = em.createQuery(jpql).getResultList();
//
//            for (Object[] item : items1)
//                System.out.println(item[0] + "/" + item[1]);

            // 제공된 이름, 직책, 연봉으로 사원 조회 : 다중 쿼리
            // 직책이 IT_PROG인 사원 조회
            // 연봉이 10000이상인 사원 조회
            // 직책이 IT_PROG이고 연봉이 6000 이상인 사원 조회
            String fname = null;
            String jobid = "IT_PROG";
            Long sal = 6000L;

            jpql = "select e from Employee e";
            List<String> cndtns = new ArrayList<>(); // 조건절 저장 변수

            if (fname != null) {
                cndtns.add("fname like concat('%', :fname, '%')");
            }
            if (jobid != null) {
                cndtns.add(" jobid = :jobid ");
            }
            if (sal != null) {
                cndtns.add(" sal >= :sal ");
            }
            if(!cndtns.isEmpty()){ // 조건식이 하나라도 존재한다면
                jpql += " where " + String.join(" and ",cndtns);
            }


            TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);

            if (fname != null){
                query.setParameter("fname",fname);
            }
            if (jobid != null) {
                query.setParameter("jobid",jobid);
            }
            if (sal != null) {
                query.setParameter("sal",sal);
            }

            List<Employee> emps = query.getResultList();
            for (Employee e : emps){
                System.out.println(e);
            }

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
