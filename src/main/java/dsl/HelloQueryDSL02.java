package dsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Visitor;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import model.*;
import model.QDepartment;
import model.QEmployee;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.List;

public class HelloQueryDSL02 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try{
            // 쿼리 객체 준비 -> 롬북으로 만들어주지 않음 : 비공식이라서 그런듯
            QEmployee qemp = QEmployee.employee; // 참조할 테이블이라고 생각하면 된다.
            QDepartment qdept = QDepartment.department;
            JPAQueryFactory query = new JPAQueryFactory(em);

            // 조회 - 전체 사원
            List<Employee> emps = query.selectFrom(qemp).fetch();
//            for(Employee e : emps) {
//                System.out.println(e);
//            }

            // 조회 - 일부 사원, 페이징, (offset,limit)
//            emps = query.selectFrom(qemp).offset(30).limit(15).fetch();
//            for (Employee e : emps) {
//                System.out.println(e);
//            }

            // 사원 데이터 조회 - select(컬럼명).from(객체명).where(조건).fetch();
            // 필터링 - 전체 데이터를 가져오는것이 아니라서 select로 작성
            // 조건 검색 : where, 직책인 사원 직책, 이름, 고용일 조회
//            List<Tuple> items = query.select(qemp.jobid,qemp.fname,qemp.hdate).from(qemp).where(qemp.jobid.eq("IT_PROG")).fetch();
//            for(Tuple e : items) {
//                System.out.println(e);
//            }

            // 정렬 : orderby, 부서번호 -> sql문과 비슷하게 작성하므로 편하다
//            query = new JPAQueryFactory(em);
//            emps = query.selectFrom(qemp).orderBy(qemp.deptno.asc()).fetch();

//            System.out.println(emps);

//            for(Employee e : emps) {
//                System.out.println(e);
//            }

            // 조건 검색 : where, 직책인 사원 조회
//            emps = query.selectFrom(qemp).from(qemp).where(qemp.jobid.eq("IT_PROG")).fetch();
//            for(Employee e : emps) {
//                System.out.println(e);
//            }

            // 조건 검색: 연봉이 14000 이상인 조회
            //  where(qemp.sal.gt(원하는 조건의 숫자));  원하는 조건 보다 큰 경우에만 조회
            //  where(qemp.sal.goe(원하는 조건의 숫자)); 원하는 조건 이상인 경우에만 조회
            //emps = query.selectFrom(qemp).from(qemp).where(qemp.sal.gt(20000)).fetch(); // 20000 보다 큰 경우 출력
//            emps = query.selectFrom(qemp).from(qemp).where(qemp.sal.goe(14000)).fetch(); // 14000 이상인 경우 출력
//            for(Employee e : emps) {
//                System.out.println(e);
//            }

            //직책수 조회 1
//            query = new JPAQueryFactory(em);
//            List<Long> cnt = query.select(qemp.jobid.count()).from(qemp).fetch();
//
//            System.out.println(cnt);

            //직책수 조회 2
//            Long cnt2 = query.select(qemp.jobid).from(qemp).fetchCount();
//
//            System.out.println(cnt2);

            //직책수 조회 3
//            cnt = query.select(qemp.jobid.countDistinct()).from(qemp).fetch();
//
//            System.out.println(cnt);

            //직책수 조회 4
//            cnt2 = query.select(qemp.jobid).distinct().from(qemp).fetchCount();
//
//            System.out.println(cnt2);

            // 그룹핑 : 직책별 최대, 최소, 평균 연봉, 직책 수 조회
//            query = new JPAQueryFactory(em);
//            List<Tuple> items = query.select(qemp.jobid,qemp.sal.max(),qemp.sal.min(),qemp.sal.avg(),qemp.jobid.count()).from(qemp).groupBy(qemp.jobid).fetch();
//
//            for (Tuple e : items) {
//                System.out.println(e);
//            }

            // 그룹핑한 결과 직책수로 정렬 : 직책별 최대, 최소, 평균 연봉, 직책 수 조회
//            StringPath jbcnt = Expressions.stringPath("jbcnt"); // 별칭 정의
//            query = new JPAQueryFactory(em);
            //items = query.select(qemp.jobid,qemp.sal.max(),qemp.sal.min(),qemp.sal.avg(),qemp.sal.count()).from(qemp).groupBy(qemp.jobid).orderBy(qemp.sal.count().desc()).fetch();
            // 이렇게 작성하기엔 너무 코드가 길다
//            items = query.select( qemp.jobid,qemp.sal.max(),qemp.sal.min(),qemp.sal.avg(),qemp.jobid.count().as("jbcnt") ).from(qemp).groupBy(qemp.jobid).orderBy( jbcnt.desc() ).fetch();
            // 도긴개긴이다. 작성되는 코드들도 너무 많다. 차리리 전자가 좋다.
            // 이래나 저래나 sql문을 많이 작성하면 모를까 별로다.

//            for (Tuple e : items) {
//                System.out.println(e);
//            }

            // 서브 쿼리1 : 평균 연봉보다 작게 받는 사원들 조회
//            query = new JPAQueryFactory(em);
//            JPQLQuery<Double> subqry = query.select(qemp.sal.avg()).from(qemp);
            // 주 쿼리 - 사원조회
//            List<Employee> list = query.selectFrom(qemp).where(qemp.sal.lt(subqry)).fetch();
//
//            for (Employee e : list) {
//                System.out.println(e);
//            }

            // 평균 연봉보다 작게 받는 사원수 조회
//            List<Long> empl = query.select(qemp.jobid.count()).from(qemp).where(qemp.sal.lt(subqry)).fetch();
//
//                System.out.println(empl);


            // 서브 쿼리2 - join : IT부서에 근무중인 사원들의 이름, 직책, 부서명 조회
            // employee 테이블에서 jobid에 직책에 IT가 붙은 사람들만 출력
//            query = new JPAQueryFactory(em);
//            JPQLQuery<String> subqry3 = query.select(qemp.jobid).from(qemp).where(qemp.jobid.like("%IT%"));
//
//            List<Tuple> item1 = query.select(qemp.fname,qemp.jobid,qemp.sal).from(qemp).where(qemp.jobid.in(subqry3)).fetch();
//            for (Tuple e: item1) {
//                System.out.println(e);
//            }

            // 서브 쿼리3 : IT부서에 근무중인 사원들의 이름, 직책, 부서명 조회
            //employee 테이블과 department 테이블의 조인을 통해서 department-id와 일치된 값만 출력
//            JPQLQuery<Long> subqry2 = JPAExpressions.select(qdept.deptno).from(qdept).where(qdept.dname.eq("IT"));
//
//            List<Tuple> items = query.select(qemp.fname,qemp.jobid,qemp.sal).from(qemp).where(qemp.deptno.eq(subqry2)).fetch();
//
//            for (Tuple e : items) {
//                System.out.println(e);
//            }

            // Join : 부서번호가 60번인 사원들의 이름, 직책, 부서명 조회
//            query = new JPAQueryFactory(em);
//            JPQLQuery<Long> subqry1 = query.select(qemp.deptno).from(qemp).join(qemp.department, qdept)
//                    .where(qdept.deptno.eq(60L));
//
//            List<Tuple> item = query.select(qemp.fname,qemp.jobid,qemp.deptno).from(qemp).where(qemp.deptno.in(subqry1)).fetch();
//            for (Tuple e: item) {
//                System.out.println(e);
//            }

            // 동적 쿼리
            // 직책이 IT_PROG인 사원 조회 "IT_PROG"
            // 연봉이 10000이상인 사원 조회
            // 직책이 IT_PROG이고 연봉이 6000 이상인 사원 조회
            String fname = "Steven";
            String jobid = null;
            Integer sal = null;

            // 동적 쿼리의 경우 BooleanExpression 및 BooleanBuilder 사용됨
            query = new JPAQueryFactory(em);
            List<Employee> demp = query.selectFrom(qemp).where(
                    (fname != null) ? qemp.fname.contains(fname) : null,
                    StringUtils.isNotEmpty(jobid) ? qemp.jobid.eq(jobid) : null ,
                    (sal != null) ? qemp.sal.goe(sal) : null
//                    StringUtils.isNotEmpty(sal+"") ? qemp.sal.goe(sal) : null
            // StringUtils.isNotEmpty ,isNotBlank는 ()안에 값이 String 타입이어야한다.
            ).fetch();

            for (Employee e : demp) {
                System.out.println(e);
            }

            // 동적 쿼리2

            BooleanExpression condition2 = qemp.jobid.contains("IT_PROG");
            BooleanExpression condition3 = qemp.sal.goe(6000);

            query = new JPAQueryFactory(em);
            BooleanBuilder bemps = new BooleanBuilder();
//            if(condition1 != null) {
//                // cndtns.add("fname like concat('%', :fname, '%')");
//                bemps.and(condition1);
//            }

            if(condition2 != null) {
                // cndtns.add(" jobid = :jobid ");
                bemps.and(condition2);
            }

            if(condition3 != null) {
                // cndtns.add(" sal >= :sal ");
                bemps.and(condition3);
            }

//            JPAQuery<Employee> blist = query.select(qemp)
//                    .from(qemp)
//                    .where(bemps);

            List<Tuple> result = query.select(qemp.fname,qemp.jobid,qemp.sal).from(qemp).where(bemps).fetch();

            for (Tuple e : result) {
                System.out.println(e);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
