package jpa;

import model.Department;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HelloJPA04 {
    public static void main(String[] args) {
    // jpa 객체 초기화 : emf -> em -> tx
        // 보통 에러가 발생하는 이유는 persistence.xml에서 잘못 설정해서
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            // 데이터 추가 : persist(대상)
            Department dept = new Department();
            dept.setDname("IT");
            dept.setMgrid(103);
            dept.setLocid(1400);

            tx.begin();
                em.persist(dept);
            tx.commit();

            // 데이터 조회 : find(객체, 기본키 값)
            tx.begin();
                dept = em.find(Department.class,1);
                em.persist(dept);
            tx.commit();
             //데이터 수정 : setXxx(변경값)
            tx.begin();
                dept = em.find(Department.class,2); // 수정할 객체 찾기
                dept.setDname("jobs"); //  값 변경
                 em.persist(dept);
            tx.commit();
             //데이터 삭제 : remove(대상)
             tx.begin();
                dept = em.find(Department.class,3); // 삭제할 객체 찾기
                em.remove(dept);
             tx.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
