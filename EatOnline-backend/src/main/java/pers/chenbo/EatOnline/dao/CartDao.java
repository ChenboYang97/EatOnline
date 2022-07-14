package pers.chenbo.EatOnline.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pers.chenbo.EatOnline.entity.Cart;
import pers.chenbo.EatOnline.entity.OrderItem;

import java.util.Iterator;
import java.util.List;

@Repository
public class CartDao {

    @Autowired
    private SessionFactory sessionFactory;

    private void removeCartItem(OrderItem orderItem) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(orderItem);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void removeAllCartItems(Cart cart) {
        List<OrderItem> list = cart.getOrderItemList();
        Iterator<OrderItem> i = list.iterator();
        while (i.hasNext()) {
            OrderItem curr = i.next();
            i.remove();
            removeCartItem(curr);
        }
    }

    private void updateCartItem(OrderItem orderItem) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(orderItem);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void increaseQuantityInCartItem(OrderItem orderItem) {
        orderItem.setQuantity(orderItem.getQuantity() + 1);
        updateCartItem(orderItem);
    }

    public void decreaseQuantityInCartItem(OrderItem orderItem) {
        if (orderItem.getQuantity() > 1) {
            orderItem.setQuantity(orderItem.getQuantity() - 1);
            updateCartItem(orderItem);
        } else {
            System.out.println("Quantity is less than 1!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            removeCartItem(orderItem);
        }
    }
}
