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
            // why don't we need to update cart below?
            // why do we need to remove the cart below? TODO
            Cart cart = cartItem.getCart();
            cart.getOrderItemList().remove(cartItem);

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
//    public void removeCartItem(int orderItemId) {
//        Session session = null;
//        try {
//            session = sessionFactory.openSession();
//            OrderItem cartItem = session.get(OrderItem.class, orderItemId);
//            Cart cart = cartItem.getCart();
//            cart.getOrderItemList().remove(cartItem);
//
//            session.beginTransaction();
//            session.delete(cartItem);
//            session.getTransaction().commit();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            if (session != null) {
//                session.getTransaction().rollback();
//            }
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//    }

    public void removeAllCartItems(Cart cart) {
        for (OrderItem orderItem : cart.getOrderItemList()) {
            removeCartItem(orderItem.getId());
        }
    }
}
