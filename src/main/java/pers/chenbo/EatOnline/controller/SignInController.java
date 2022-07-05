package pers.chenbo.EatOnline.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.chenbo.EatOnline.entity.Customer;
import pers.chenbo.EatOnline.service.CustomerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SignInController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    // Only process the failed login request here
    // If login successfully, it will automatically redirect to home page
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@RequestParam(value = "error") String error, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Map<String, Object> data = new HashMap<>();
        data.put("message", "bad credentials");
        response.getOutputStream().println(objectMapper.writeValueAsString(data));
    }
}
