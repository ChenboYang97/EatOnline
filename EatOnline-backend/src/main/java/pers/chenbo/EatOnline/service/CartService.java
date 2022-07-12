package pers.chenbo.EatOnline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pers.chenbo.EatOnline.dao.CartDao;
import pers.chenbo.EatOnline.dao.OrderItemDao;
import pers.chenbo.EatOnline.entity.Cart;
import pers.chenbo.EatOnline.entity.Customer;
import pers.chenbo.EatOnline.entity.OrderItem;

@Service
public class CartService {

    @Autowired
    private CartDao cartDao;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private MenuInfoService menuInfoService;
    @Autowired
    private OrderItemDao orderItemDao;

    public Cart getCart() {
        Customer customer = getCustomerByContext();
        if (customer != null) {
            Cart cart = customer.getCart();
            double totalPrice = 0;
            for (OrderItem orderItem : cart.getOrderItemList()) {
                totalPrice += orderItem.getPrice() * orderItem.getQuantity();
            }
            cart.setTotalPrice(totalPrice);
            return cart;
        }
        return new Cart();
    }

    public Cart updateCartByDecrease(int menuId) {
        Customer customer = getCustomerByContext();
        if (customer != null) {
            OrderItem orderItem = orderItemDao.getOrderItem(menuInfoService.getMenuItem(menuId),customer.getCart());
            cartDao.decreaseQuantityInCartItem(orderItem);
        }
        return getCart();
    }

    public Cart updateCartByIncrease(int menuId) {
        Customer customer = getCustomerByContext();
        if (customer != null) {
            OrderItem orderItem = orderItemDao.getOrderItem(menuInfoService.getMenuItem(menuId),customer.getCart());
            cartDao.increaseQuantityInCartItem(orderItem);
        }
        return getCart();
    }

    public void cleanCart() {
        Customer customer = getCustomerByContext();
        if (customer != null) {
            cartDao.removeAllCartItems(customer.getCart());
        }
    }

    // 把从context中提取custoemr的function放到customerService中去是否可以？其他的service都要依赖这个customerService？TODO
    private Customer getCustomerByContext() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();
        Customer customer = customerService.getCustomer(email);
        return customer;
    }
}
