package com.jiankun.mall.service.impl;

import com.jiankun.mall.constant.MqConstant;
import com.jiankun.mall.mapper.CartMapper;
import com.jiankun.mall.mapper.OrderItemMapper;
import com.jiankun.mall.mapper.OrderMapper;
import com.jiankun.mall.pojo.Order;
import com.jiankun.mall.pojo.OrderItem;
import com.jiankun.mall.pojo.query.CartQuery;
import com.jiankun.mall.pojo.vo.CartVO;
import com.jiankun.mall.pojo.vo.OrderVO;
import com.jiankun.mall.service.IOrderService;
import com.jiankun.mall.util.MultiDelayMessage;
import com.jiankun.mall.util.SnowFlake;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void add(Order order) {
        long orderNo = snowFlake.nextId();
        CartQuery cartQuery = new CartQuery();
        cartQuery.setUserId(order.getUserId());
        cartQuery.setChecked(1);
        List<CartVO> list = cartMapper.list(cartQuery);
        BigDecimal payment = BigDecimal.ZERO;
        for (CartVO cartVO : list) {
            OrderItem orderItem = new OrderItem();
            orderItem.setUserId(order.getUserId());
            orderItem.setOrderNo(orderNo);
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
        order.setOrderNo(orderNo);
        order.setPayment(payment);
        orderMapper.insertSelective(order);

        //发送延时消息，超过30分钟未支付取消订单
        MultiDelayMessage<Long> message = new MultiDelayMessage<>(orderNo, 10000L, 10000L, 10000L, 15000L, 15000L, 30000L, 30000L);
        Long delayValue = message.removeNextDelay();
        rabbitTemplate.convertAndSend(MqConstant.DELAY_EXCHANGE, MqConstant.DELAY_ORDER_ROUTING_KEY, message, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setDelayLong(delayValue);
                return message;
            }
        });
    }

    @Override
    public List<OrderVO> list(Integer userId) {
        return orderMapper.list(userId);
    }

    @Override
    public Order selectById(Long orderNo) {
        return orderMapper.selectByPrimaryKey(orderNo);
    }

    @Override
    public void updateStatus(Long orderNo, Integer status) {
        orderMapper.updateStatus(orderNo, status);
    }

    @Override
    public void updateByPrimaryKeySelective(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }
}
