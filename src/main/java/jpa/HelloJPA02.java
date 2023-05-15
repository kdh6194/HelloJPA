package jpa;

import model.SungJuk;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HelloJPA02 {
    public static void main(String[] args) {
    // jpa 객체 초기화 : emf -> em -> tx
        // 보통 에러가 발생하는 이유는 persistence.xml에서 잘못 설정해서
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            // 데이터 추가 : persist(대상)
            SungJuk sj = new SungJuk();
            sj.setName("명오");
            sj.setKor(65);
            sj.setEng(30);
            sj.setMat(55);

            tx.begin();
                em.persist(sj);
            tx.commit();

            // 데이터 조회 : find(객체, 기본키 값)
//            tx.begin();
//                sj = em.find(SungJuk.class,3);
//                em.persist(sj);
//            tx.commit();
            // 데이터 수정 : setXxx(변경값)
            //tx.begin();
                // sj = em.find(SungJuk.class,4); // 수정할 객체 찾기
                // sj.setName("지현"); //  값 변경
                // em.persist(sj);
            //tx.commit();
            // 데이터 삭제 : remove(대상)
            // tx.begin();
            //    sj = em.find(SungJuk.class,15); // 삭제할 객체 찾기
            //    em.remove(sj);
            // tx.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
