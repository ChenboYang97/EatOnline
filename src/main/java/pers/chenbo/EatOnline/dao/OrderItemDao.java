package pers.chenbo.EatOnline.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pers.chenbo.EatOnline.entity.MenuItem;
import pers.chenbo.EatOnline.entity.OrderItem;
import pers.chenbo.EatOnline.entity.Restaurant;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderItemDao {

    @Autowired
    private SessionFactory sessionFactory;

    // is this a way that try-with-resource and rollback can work together? TODO
    public void saveItemToCart(OrderItem orderItem) {
        Transaction tx = null;
        try(Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(orderItem);
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
    }
//    public void saveItemToCart(OrderItem orderItem) {
//        Session session = null;
//        try {
//            session = sessionFactory.openSession();
//            session.beginTransaction();
//            session.save(orderItem);
//            session.getTransaction().commit();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            if (session != null) session.getTransaction().rollback();
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//    }
}
