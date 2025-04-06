package com.jiankun.mall.controller;

import com.jiankun.mall.pojo.vo.OrderItemVO;
import com.jiankun.mall.service.IOrderItemService;
import com.jiankun.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/4/6 21:24
 */
@RestController
@RequestMapping("/orderItem")
public class OrderItemController {
    @Autowired
    private IOrderItemService orderItemService;

    @RequestMapping("/selectOrderItemByOrderNo")
    public Result<OrderItemVO> selectOrderItemByOrderNo(Long orderNo) {
        List<OrderItemVO> list = orderItemService.selectOrderItemByOrderNo(orderNo);
        return Result.ok(list);
    }
}
