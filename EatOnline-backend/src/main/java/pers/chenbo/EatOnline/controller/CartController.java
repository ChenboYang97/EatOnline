package pers.chenbo.EatOnline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.chenbo.EatOnline.entity.Cart;
import pers.chenbo.EatOnline.service.CartService;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    @ResponseBody
    public Cart getCart() {
        return cartService.getCart();
    }

    @RequestMapping(value = "/cart/{menuId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @ResponseBody
    public Cart updateCartByDecrease(@PathVariable("menuId") int menuId) {
        return cartService.updateCartByDecrease(menuId);
    }

    @RequestMapping(value = "/cart/{menuId}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @ResponseBody
    public Cart updateCartByIncrease(@PathVariable("menuId") int menuId) {
        return cartService.updateCartByIncrease(menuId);
    }
}
