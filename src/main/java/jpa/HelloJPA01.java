package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HelloJPA01 {
    public static void main(String[] args) {
    // jpa 객체 초기화 : emf -> em -> tx
        // 보통 에러가 발생하는 이유는 persistence.xml에서 잘못 설정해서
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
