package pers.chenbo.EatOnline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pers.chenbo.EatOnline.dao.OrderItemDao;
import pers.chenbo.EatOnline.entity.Customer;
import pers.chenbo.EatOnline.entity.MenuItem;
import pers.chenbo.EatOnline.entity.OrderItem;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private MenuInfoService menuInfoService;
    @Autowired
    private CustomerService customerService;

    public void saveOrderItem(int menuId) {

        final OrderItem orderItem = new OrderItem();
        final MenuItem menuItem = menuInfoService.getMenuItem(menuId);

        // Obtain the current logged-in user from context
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();
        Customer customer = customerService.getCustomer(email);

        orderItem.setMenuItem(menuItem);
        orderItem.setCart(customer.getCart());
        orderItem.setPrice(menuItem.getPrice());
        orderItem.setQuantity(1);
        orderItemDao.saveItemToCart(orderItem);
    }
}