package com.jiankun.mall.controller;

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
}
