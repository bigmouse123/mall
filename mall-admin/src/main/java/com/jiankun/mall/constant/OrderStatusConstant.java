package com.jiankun.mall.constant;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/25 9:27
 */
public interface OrderStatusConstant {
    //订单状态:0-已取消-1-未付款，2-已付款，3-已发货，4-交易成功，5-交易关闭
    Integer CANCEL = 0;
    Integer UNPAID = 1;
    Integer PAID = 2;
    Integer DELIVERED = 3;
    Integer SUCCESS = 4;
    Integer CLOSE = 5;
}
