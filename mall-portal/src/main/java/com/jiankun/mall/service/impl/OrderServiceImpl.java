package com.jiankun.mall.service.impl;

import com.jiankun.mall.mapper.CartMapper;
import com.jiankun.mall.mapper.OrderItemMapper;
import com.jiankun.mall.mapper.OrderMapper;
import com.jiankun.mall.pojo.Order;
import com.jiankun.mall.pojo.OrderItem;
import com.jiankun.mall.pojo.query.CartQuery;
import com.jiankun.mall.pojo.vo.CartVO;
import com.jiankun.mall.pojo.vo.OrderVO;
import com.jiankun.mall.service.IOrderService;
import com.jiankun.mall.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/17 14:56
 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private SnowFlake snowFlake;

    @Override
    public void add(Order order) {
        long id = snowFlake.nextId();
        CartQuery cartQuery = new CartQuery();
        cartQuery.setUserId(order.getUserId());
        cartQuery.setChecked(1);
        List<CartVO> list = cartMapper.list(cartQuery);
        BigDecimal payment = BigDecimal.ZERO;
        for (CartVO cartVO : list) {
            OrderItem orderItem = new OrderItem();
            orderItem.setUserId(order.getUserId());
            orderItem.setOrderNo(id);
            orderItem.setProductId(cartVO.getProductId());
            orderItem.setProductName(cartVO.getProductName());
            orderItem.setProductImage(cartVO.getProductMainImage());
            orderItem.setCurrentUnitPrice(cartVO.getProductPrice());
            orderItem.setQuantity(cartVO.getQuantity());

            BigDecimal productPrice = cartVO.getProductPrice();
            BigDecimal quantity = BigDecimal.valueOf(orderItem.getQuantity());
            BigDecimal totalPrice = productPrice.multiply(quantity);
            payment = payment.add(totalPrice);
            orderItem.setTotalPrice(totalPrice);

            orderItemMapper.insertSelective(orderItem);
            cartMapper.deleteByPrimaryKey(cartVO.getId());
        }
        order.setOrderNo(id);
        order.setPayment(payment);
        orderMapper.insertSelective(order);
    }

    @Override
    public List<OrderVO> list(Integer userId) {
        return orderMapper.list(userId);
    }
}
