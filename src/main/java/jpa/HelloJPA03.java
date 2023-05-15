package jpa;

import model.Employees;


import javax.persistence.*;
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
                //Employees emp = new Employees();

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
                    //emp = em.find(Employees.class,1);
                    //em.persist(emp);
                //tx.commit();
             //데이터 수정 : setXxx(변경값)
                //tx.begin();
                     //emp = em.find(Employees.class,2); // 수정할 객체 찾기
                     //emp.setFname("Oking"); //  값 변경
                     //em.persist(emp);
                //tx.commit();
             //데이터 삭제 : remove(대상)
                 //tx.begin();
                    //emp = em.find(Employees.class,3); // 삭제할 객체 찾기
                    //em.remove(emp);
                 //tx.commit();

    // JPQL
        // 사원 데이터 조회 - TypedQuery
            // createQuery(질의문, 리턴될 객체 종류)
            String jpql = "select e from Employees as e";
            //List<Employees> emps = em.createQuery(jpql,Employees.class).getResultList();

            //for (Employees emp: emps){System.out.println(emp);}
        // 사원 데이터 조회 - Query : 이름, 부서번호, 입사일
            // createQuery(질의문)
        jpql = "select fname, deptno, hdate from Employees e";
            //List<Object[]> items = em.createQuery(jpql).getResultList();

            //for(Object[] item : items) {System.out.println(item[0] + "/" + item[1] + "/" + item[2]);}
        // 사원 직책 조회 - 직책이 IT_PROG인 사원
            // 파라메타 바인딩 - :파라메타명, ?순번
            jpql = "select e from Employees e where jobid = :jobid "; // -> 이름 기반
//            jpql = "select e from Employees e where jobid = ?1 "; -> 위치 기반
            //TypedQuery<Employees> query = em.createQuery(jpql,Employees.class);
            //query.setParameter("jobid","IT_PROG"); // -> 이름 기반
//            query.setParameter(1,"IT_PROG"); // -> 숫자 기반
            //query.getResultList();

            //for (Employees emp: emps){System.out.println(emp);}

            // 사원 평균 연봉 조회 - TypedQuery
            // createQuery(질의문, 리턴될 객체 종류)
            jpql = "select avg(sal) from Employees e";
//            Double avgsal = em.createQuery(jpql, Double.class).getSingleResult();
//
//            System.out.println(avgsal);

            // 사원 직책수 조회
            jpql = "select count(jobid) from Employees e";
//            Long tot = em.createQuery(jpql, Long.class).getSingleResult();
//
//            System.out.println(tot);

            // jobid로 정렬 후 3페이지 조회 : 페이징 (페이지당 출력건수)
            // setFirstResult(startpos) : 페이징 시작 위치
            // setMaxResult(getdatacnt) : 조회할 데이터 수
            jpql = "select e from Employees e order by jobid";
//            List<Employees> pemps = em.createQuery(jpql, Employees.class).setFirstResult(30).setMaxResults(15).getResultList();
//
//            for (Employees emp: pemps){System.out.println(emp);}

            // 직책별 평균 연봉 조회
            jpql = "select jobid, avg(sal) from Employees e group by jobid";
            List<Object[]> select = em.createQuery(jpql).getResultList();

            for (Object[] emp: select){System.out.println(emp[0] +  "/" + emp[1] + "/" + emp[2]);}

            // 사원 이름, 직책, 부서명 조회 : join
            //jpql = "select e.fname, e.jobid from Employees e inner join e.dept d";
            //List<Object[]> items = em.createQuery(jpql).getResultList();

            //for (Object[] item : items)
            //    System.out.println(item[0] + "/" + item[1] + "/" + item[2]);



        } catch (Exception e){
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
