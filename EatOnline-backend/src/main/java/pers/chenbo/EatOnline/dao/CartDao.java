package pers.chenbo.EatOnline.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pers.chenbo.EatOnline.entity.Cart;
import pers.chenbo.EatOnline.entity.OrderItem;

@Repository
public class CartDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void removeCartItem(int orderItemId) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            OrderItem cartItem = session.get(OrderItem.class, orderItemId);

            tx = session.beginTransaction();
            session.delete(cartItem);
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public void removeAllCartItems(Cart cart) {
        for (OrderItem orderItem : cart.getOrderItemList()) {
            removeCartItem(orderItem.getId());
        }
    }
}
