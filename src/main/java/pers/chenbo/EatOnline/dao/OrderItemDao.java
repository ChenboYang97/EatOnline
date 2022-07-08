package pers.chenbo.EatOnline.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pers.chenbo.EatOnline.entity.OrderItem;


@Repository
public class OrderItemDao {

    @Autowired
    private SessionFactory sessionFactory;

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
}
