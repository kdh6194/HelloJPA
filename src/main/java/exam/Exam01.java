package exam;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import model.Orders;
import model.QCustumers;
import model.QOrders;
import model.QProducts;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


public class Exam01 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

    try{
        QOrders ord = QOrders.orders;
        QCustumers cust = QCustumers.custumers;
        QProducts prod = QProducts.products;
        JPAQueryFactory query = new JPAQueryFactory(em);
        List<Orders> jord = query.selectFrom(ord).fetch();

        for (Orders e : jord) {
            System.out.println(e);
        }

        // 메인 쿼리
         query = new JPAQueryFactory(em);
//        List<Tuple> rs = query.select(ord.userid).from(ord).join(ord.product, prod).where().fetch();

    }catch (Exception ex){
        ex.printStackTrace();
        tx.rollback();
    } finally {
        em.close();
        emf.close();
    }

    }
}
