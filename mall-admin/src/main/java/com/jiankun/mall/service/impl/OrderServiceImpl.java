package com.jiankun.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiankun.mall.mapper.OrderItemMapper;
import com.jiankun.mall.pojo.OrderItem;
import com.jiankun.mall.pojo.query.OrderQuery;
import com.jiankun.mall.pojo.vo.OrderItemVO;
import com.jiankun.mall.service.IOrderService;
import com.jiankun.mall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/29 17:10
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public PageResult<OrderItemVO> list(OrderQuery orderQuery) {
        PageHelper.startPage(orderQuery.getPage(), orderQuery.getLimit());
        List<OrderItemVO> list = orderItemMapper.list(orderQuery);
        PageInfo<OrderItemVO> pageInfo = new PageInfo<>(list);
        int count = (int) pageInfo.getTotal();
        return new PageResult<>(0, "", count, list);
    }
}
