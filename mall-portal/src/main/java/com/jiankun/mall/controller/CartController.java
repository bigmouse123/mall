package com.jiankun.mall.controller;

import com.jiankun.mall.pojo.Cart;
import com.jiankun.mall.pojo.User;
import com.jiankun.mall.pojo.vo.CartVO;
import com.jiankun.mall.service.ICartService;
import com.jiankun.mall.util.Result;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/14 16:35
 */
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    @RequestMapping("/add")
    public Result add(Cart cart, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new Result(Result.NOT_LOGIN, "未登录");
        }
        cart.setUserId(user.getId());
        cartService.add(cart);
        return Result.ok("加入购物车成功");
    }

    @RequestMapping("/list")
    public Result list(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<CartVO> list = cartService.list(user.getId());
        return Result.ok(list);
    }
}
