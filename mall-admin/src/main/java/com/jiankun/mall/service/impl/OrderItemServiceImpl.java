package com.jiankun.mall.service.impl;

import com.jiankun.mall.mapper.OrderItemMapper;
import com.jiankun.mall.pojo.vo.OrderItemVO;
import com.jiankun.mall.service.IOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/4/6 21:25
 */
@Service
public class OrderItemServiceImpl implements IOrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItemVO> selectOrderItemByOrderNo(Long orderNo) {
        return orderItemMapper.selectOrderItemByOrderNo(orderNo);
    }
}
