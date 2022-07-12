package pers.chenbo.EatOnline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pers.chenbo.EatOnline.dao.CustomerDao;
import pers.chenbo.EatOnline.entity.Cart;
import pers.chenbo.EatOnline.entity.Customer;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public void signUp(Customer customer) {
        Cart cart = new Cart();
        customer.setCart(cart);
        customer.setEnabled(true);

        customerDao.signUp(customer);
    }

    public void signIn(Customer customer) {

    }

    public Customer getCustomer(String email) {
        return customerDao.getCustomer(email);
    }

    // 把从context中提取custoemr的function放到customerService中去是否可以？其他的service都要依赖这个customerService？TODO
    public Customer getCustomerByContext() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();
        Customer customer = getCustomer(email);
        return customer;
    }
}
