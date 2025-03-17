package com.jiankun.mall.controller;

import com.jiankun.mall.pojo.Shipping;
import com.jiankun.mall.pojo.User;
import com.jiankun.mall.service.IShippingService;
import com.jiankun.mall.util.Result;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/17 8:44
 */
@RestController
@RequestMapping("/shipping")
public class ShippingController {
    @Autowired
    private IShippingService shippingService;

    @RequestMapping("/list")
    public Result list(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Shipping> list = shippingService.list(user.getId());
        return Result.ok(list);
    }
}
