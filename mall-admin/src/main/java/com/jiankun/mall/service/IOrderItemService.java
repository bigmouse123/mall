package com.jiankun.mall.service;

import com.jiankun.mall.pojo.vo.OrderItemVO;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/4/6 21:24
 */
public interface IOrderItemService {
    List<OrderItemVO> selectOrderItemByOrderNo(Long orderNo);
}
