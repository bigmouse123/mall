package com.jiankun.mall.service;

import com.jiankun.mall.pojo.OrderItem;
import com.jiankun.mall.pojo.query.OrderQuery;
import com.jiankun.mall.pojo.vo.OrderItemVO;
import com.jiankun.mall.util.PageResult;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/29 17:09
 */
public interface IOrderService {
    public PageResult<OrderItemVO> list(OrderQuery orderQuery);
}
