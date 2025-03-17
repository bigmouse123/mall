package com.jiankun.mall.controller;

import com.jiankun.mall.pojo.Order;
import com.jiankun.mall.pojo.User;
import com.jiankun.mall.service.IOrderService;
import com.jiankun.mall.util.Result;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/17 14:55
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping("/add")
    public Result add(Order order, HttpSession session) {
        System.out.println(order);
        User user = (User) session.getAttribute("user");
        order.setUserId(user.getId());
        orderService.add(order);
        return Result.ok("生成订单成功");
    }
}
