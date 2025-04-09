package com.jiankun.mall.listener;

import com.jiankun.mall.constant.MqConstant;
import com.jiankun.mall.constant.OrderStatusConstant;
import com.jiankun.mall.pojo.Order;
import com.jiankun.mall.service.IOrderService;
import com.jiankun.mall.util.MultiDelayMessage;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/24 10:53
 */
@Component
public class SpringRabbitListener {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private IOrderService orderService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(MqConstant.DELAY_ORDER_QUEUE),
            exchange = @Exchange(name = MqConstant.DELAY_EXCHANGE, delayed = "true"), //type类型是：x-delayed-message
            key = MqConstant.DELAY_ORDER_ROUTING_KEY
    ))
    public void listenDelayMessage(MultiDelayMessage message) {
        System.out.println("SpringRabbitListener.listenDelayMessage message=" + message);
        //1.获取订单信息中的orderNo
        Long orderNo = (Long) message.getData();
        //2.查询订单，判断状态
        Order order = orderService.selectById(orderNo);
        //订单不存在或订单不处于未支付状态
        if (order == null) {
            System.out.println("订单不存在");
            return;
        }
        if (order.getStatus() != 1) {
            System.out.println("订单已支付");
            return;
        }
        //3.订单处于未支付状态
        //判断订单是否还有剩余延时时间
        if (message.hasNextDelay()) {//还有剩余延时时间
            //重新发送延迟消息
            Long delayValue = message.removeNextDelay();
            System.out.println("重新发送延迟时间:" + delayValue + "ms");
            rabbitTemplate.convertAndSend(MqConstant.DELAY_EXCHANGE, MqConstant.DELAY_ORDER_ROUTING_KEY, message, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().setDelayLong(delayValue);
                    return message;
                }
            });
        } else {//没有剩余延迟时间
            //4.订单超时未支付
            System.out.println("订单超时未支付");
            order.setStatus(OrderStatusConstant.CANCEL);
            order.setEndTime(new Date());
            order.setCloseTime(new Date());
            orderService.updateByPrimaryKeySelective(order);
        }
    }
}
