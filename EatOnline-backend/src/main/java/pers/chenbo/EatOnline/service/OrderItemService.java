package pers.chenbo.EatOnline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pers.chenbo.EatOnline.dao.OrderItemDao;
import pers.chenbo.EatOnline.entity.Customer;
import pers.chenbo.EatOnline.entity.MenuItem;
import pers.chenbo.EatOnline.entity.OrderItem;

import javax.persistence.NoResultException;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private MenuInfoService menuInfoService;
    @Autowired
    private CustomerService customerService;

    public void saveOrderItem(int menuId) {

        final MenuItem menuItem = menuInfoService.getMenuItem(menuId);
        Customer customer = customerService.getCustomerByContext();
        OrderItem orderItem = orderItemDao.getOrderItem(menuItem, customer.getCart());

        if (orderItem == null) {
            orderItem = new OrderItem();
            orderItem.setMenuItem(menuItem);
            orderItem.setCart(customer.getCart());
            orderItem.setPrice(menuItem.getPrice());
            orderItem.setQuantity(1);
            orderItemDao.saveItemToCart(orderItem);
        } else {
            orderItem.setQuantity(orderItem.getQuantity() + 1);
            orderItemDao.updateItemToCart(orderItem);
        }
    }
}
