package com.jiankun.mall.controller;

import com.jiankun.mall.pojo.Cart;
import com.jiankun.mall.pojo.User;
import com.jiankun.mall.pojo.query.CartQuery;
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
    public Result list(HttpSession session, CartQuery cartQuery) {
        User user = (User) session.getAttribute("user");
        cartQuery.setUserId(user.getId());
        List<CartVO> list = cartService.list(cartQuery);
        return Result.ok(list);
    }

    @RequestMapping("/update")
    public Result update(Cart cart) {
        cartService.update(cart);
        return Result.ok("更新成功");
    }

    @RequestMapping("/updateCheckedAll")
    public Result updateCheckedAll(Integer checked, HttpSession session) {
        User user = (User) session.getAttribute("user");
        cartService.updateCheckedAll(checked, user.getId());
        return Result.ok("更新成功");
    }

    @RequestMapping("/minus")
    public Result minus(Cart cart) {
        cartService.minus(cart);
        return Result.ok();
    }

    @RequestMapping("/plus")
    public Result plus(Cart cart) {
        cartService.plus(cart);
        return Result.ok();
    }

    @RequestMapping("/deleteById")
    public Result deleteById(Integer id) {
        cartService.deleteById(id);
        return Result.ok("删除成功");
    }
}
