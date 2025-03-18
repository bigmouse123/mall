package com.jiankun.mall.service;

import com.jiankun.mall.pojo.Order;
import com.jiankun.mall.pojo.vo.OrderVO;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/17 14:56
 */
public interface IOrderService {
    void add(Order order);

    List<OrderVO> list(Integer userId);
}
