package pers.chenbo.EatOnline.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pers.chenbo.EatOnline.entity.Cart;
import pers.chenbo.EatOnline.entity.MenuItem;
import pers.chenbo.EatOnline.entity.OrderItem;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;



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

    public void updateItemToCart(OrderItem orderItem) {
        Transaction tx = null;
        try(Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.update(orderItem);
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public OrderItem getOrderItem(MenuItem menuItem, Cart cart) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<OrderItem> criteria = builder.createQuery(OrderItem.class);
            Root<OrderItem> root = criteria.from(OrderItem.class);

            Predicate[] predicates = new Predicate[2];
            predicates[0] = builder.equal(root.get("cart"), cart);
            predicates[1] = builder.equal(root.get("menuItem"), menuItem);

            criteria.select(root).where(predicates);
            OrderItem orderItem;
            try {
                orderItem = session.createQuery(criteria).getSingleResult();
            } catch(NoResultException ex) {
                System.out.println("Catch no result exception!!!!!!!!!!");
                orderItem = null;
            }
            return orderItem;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
