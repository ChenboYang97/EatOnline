package pers.chenbo.EatOnline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import pers.chenbo.EatOnline.entity.Customer;
import pers.chenbo.EatOnline.service.CustomerService;

@Controller
public class SignUpController {

    @Autowired
    private CustomerService customerService;

    // @RequestMapping defines the REST API, such as HTTP URL
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    // @RequestBody converts the request body in the http request to a backend object
    public void signUp(@RequestBody Customer customer) {
        customerService.signUp(customer);
    }
}
