package com.jiankun.mall.controller;

import com.jiankun.mall.constant.OrderStatusConstant;
import com.jiankun.mall.pojo.Order;
import com.jiankun.mall.pojo.OrderItem;
import com.jiankun.mall.pojo.query.OrderQuery;
import com.jiankun.mall.pojo.vo.OrderItemVO;
import com.jiankun.mall.pojo.vo.OrderVO;
import com.jiankun.mall.service.IOrderService;
import com.jiankun.mall.util.PageResult;
import com.jiankun.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/29 17:07
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping("/selectAll")
    public PageResult<OrderVO> selectAll(OrderQuery orderQuery) {
        PageResult<OrderVO> pageResult = orderService.selectAll(orderQuery);
        return pageResult;
    }

    @RequestMapping("update")
    public Result update(Order order) {
        orderService.update(order);
        return Result.ok("发货成功");
    }

    @RequestMapping("/delivery")
    public Result delivery(Order order) {
        order.setStatus(OrderStatusConstant.DELIVERED);
        order.setSendTime(new Date());
        orderService.update(order);
        return Result.ok("发货成功");
    }

}
