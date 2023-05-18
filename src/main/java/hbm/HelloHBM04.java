package hbm;


import model.Department;
import notmap.Empinfo;
import model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;



public class HelloHBM04 {
    public static void main(String[] args) {
        // SessionFactory 초기화
        // 어노테이션으로 적용 -> 스프링부트 작업할때 사용한다고 함
        // VO클래스 한번에 적용 가능
        Configuration cfg = new Configuration().configure();
        SessionFactory sf = cfg.addAnnotatedClass(Employee.class)
                               .addAnnotatedClass(Department.class)
                               .buildSessionFactory();
        Session sess = sf.openSession();

        try{
            // 조회 - 전체 사원 (hql을 이용)
//            Query query = sess.createQuery("from Employee ");
//            List<Employee> emps = query.getResultList();
//
//            for (Employee e : emps) {
//                System.out.println(e);
//            }

            // 조회 - 일부 사원, 페이징
            //Query<Employee> query2 = sess.createQuery("from Employee where jobid like '%IT%' ", Employee.class);
            // .createQuery에서 "from 테이블 where 조건식" sql문처럼 작성한다면 출력이 가능하다.
//            Query<Employee> query2 = sess.createQuery("from Employee where deptno = 60 ", Employee.class);
//            query2.setFirstResult(0); // 시작 위치
//            query2.setMaxResults(10); // 한 페이지에 표시할 결과 수
//
//            List<Employee> emp = query2.getResultList();
//
//            for (Employee e : emp) {
//                // 여기서 출력할 조건을 작성해도 된다.
//               // if(e.getJobid().equals("IT_PROG")) {
//                    System.out.println(e.getFname() + "/" + e.getJobid() + "/" + e.getSal());
//               //}
//            }



            // 강사님이 작성한 일부사원 조회 - 상당히 코드를 줄일 수 있다.
            // Objects[] 가 아닌 Object[] 로 사용해야한다.
//            Query query = sess.createQuery("select fname, sal from Employee order by empno");
//            List<Object[]> items = query.getResultList();
//
//            for (Object[] i:items) {
//                System.out.println(i[0] + "/" + i[1]);
//            }

            // 조회 - 부분조회2 : 이름, 연봉
            // hibernate에 추가된 기능으로 밑에 스크립트를 작성에 참고
            // "select new notmap.Empinfo(fname,sal) from Employee " 해당 스크립트를 통해 값을 가져오려면
            // 데이터를 가져오는 VO클래스에서 선언된 변수의 데이터 타입과 동일해야 정보를 가져온다.
            // 매번 클래스를 매핑하지 않더라도 클래스(어노테이션이 들어가지않은 클래스)를 선언하여
            // 사용 가능하다 -> 전체 데이터를 관장하는 클래스만 미리 매핑하고 필요한 것들만 만들어서 쓰는 느낌
//            Query query = sess.createQuery("select new notmap.Empinfo(fname,sal) from Employee ");
//
//            List<Empinfo> items = query.getResultList();
//
//            for (Empinfo e : items) {
//                System.out.println(e);
//            }

            // 조건검색 : where, 직책이IT_PROG인 사원 조회
            // 만약 숫자 기반이 아닌 이름 기반으로 찾을거라면
            // "select e from Employee e where jobid = :jobid "
            // 로 작성되어야 한다.
//            //Query query = sess.createQuery("from Employee where jobid = ?1 ");
//            Query query = sess.createQuery("from Employee where jobid = :jobid ");
//            //query.setParameter(1,"IT_PROG"); - 숫자 기반
//            query.setParameter("jobid","IT_PROG"); - 이름 기반
//            List<Employee> emps = query.getResultList();
//
//            for (Employee e : emps) {
//                System.out.println(e);
//            }

            // 조건검색: 연봉이 20000이상인 사원조회

//            Query query = sess.createQuery("from Employee where sal >= ?1 ");
//            query.setParameter(1,20000L);
//            List<Employee> emps = query.getResultList();
//
//            for (Employee e : emps) {
//                System.out.println(e);
//            }

            // 사원 직책수 조회
//            Query query = sess.createQuery("select distinct count(jobid) from Employee order by deptno desc");
//            List cnt = query.getResultList();
//
//                System.out.println(cnt);

            // 사원 평균 연봉 조회
            // round 적용 한번 알아보기
//            Query query = sess.createQuery("select avg(round(sal,10)) from Employee order by deptno desc", Double.class);
//            List<Double> cnt = query.getResultList();
//
//            System.out.println(cnt);

            // 그룹핑 : 직책수, 최대, 최소, 평균 연봉 조회
//            Query query = sess.createQuery("select  max(sal), min(sal), avg(round(sal,10)),count(jobid) from Employee");
//            List<Object[]> items = query.getResultList();
//
//            for (Object[] i : items){
//                System.out.println(i[0] + "/" + i[1] + "/" + i[2] + "/" + i[3] );
//            }

            // 서브쿼리 : 평균연봉 보다 작게 받는 사원들 조회
//            Query query = sess.createQuery("select fname, sal from Employee where sal < (select avg(sal) from Employee)");
//            List<Object[]> emps = query.getResultList();
//
//            for (Object[] i : emps){
//                System.out.println(i[0] + "/" + i[1] );
//            }

            // 부서명이 IT인 사원의 이름, 직책, 급여 조회 : 서브 쿼리
//            Query query = sess.createQuery("select fname, jobid, sal from Employee where deptno = (select deptno from Department where dname like '%IT%')");
//            List<Object[]> emps = query.getResultList();
//
//            for (Object[] e : emps){
//                System.out.println(e[0] + "/" + e[1]);
//            }

            // 부서번호가 60인 사원 이름, 직책, 부서명 조회 : join
            Query query = sess.createQuery("select e.fname, e.jobid, d.dname from Employee e inner join e.department d where e.deptno = 60   ");
            List<Object[]> items = query.getResultList();

            for (Object[] i : items){
                System.out.println(i[0] + "/" + i[1] + "/" + i[2]);
            }
            // .list() 와 .getResultList() 의 차이점 - 3.xx 버전에서 사용된 명령어 .list
            // .getSingleResult()를 사용할 때 주의점
            //  - 값이 하나인 경우에만 사용가능, 결과가 무조건 있어야함
            //    결과가 없거나,하나 이상인 경우 에러 발생


        }catch (Exception ex){
            ex.printStackTrace();
        } finally {

            sess.close();
            sf.close();

        }
    }
}
