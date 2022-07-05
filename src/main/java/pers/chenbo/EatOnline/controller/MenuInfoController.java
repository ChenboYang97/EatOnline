package pers.chenbo.EatOnline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.chenbo.EatOnline.entity.MenuItem;
import pers.chenbo.EatOnline.entity.Restaurant;
import pers.chenbo.EatOnline.service.MenuInfoService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuInfoController {

    @Autowired
    private MenuInfoService menuInfoService;

    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    // @ResponseBody annotation on a method to indicate that the return value should be written straight to
    // the HTTP response body, and it will be automatically convert to json format
    @ResponseBody
    public List<Restaurant> getRestaurants() {
        return menuInfoService.getRestaurants();
    }

    @RequestMapping(value = "/restaurant/{restaurantId}/menu", method = RequestMethod.GET)
    @ResponseBody
    // @RequestParam annotation to bind Servlet request parameters to a method argument in a controller
    // like, http://www.google.com/search?q=something&size=40, the value of q and size in it
    // @PathVariable annotation to handle template variables in the request URL mapping
    // like, http://localhost:8080/restaurant/1/menu, the restaurant id 1 of it
    public List<MenuItem> getMenus(@PathVariable("restaurantId") int restaurantId) {
        return menuInfoService.getAllMenuItem(restaurantId);
    }

//    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
//    @ResponseBody
//    public MenuItem getMenuItem() {
//        return menuInfoService.getMenuItem();
//    }
}
