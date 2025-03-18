package com.jiankun.mall.pojo.vo;

import com.jiankun.mall.pojo.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/18 10:24
 */
@Data
public class OrderVO {
    private Long orderNo;

    private Integer userId;

    private Integer shippingId;

    private BigDecimal payment;

    private Integer paymentType;

    private Integer postage;

    private Date paymentTime;

    private Date sendTime;

    private Date endTime;

    private Date closeTime;

    private Integer status;

    private Byte isDeleted;

    private Date createTime;

    private Date updateTime;

    private List<OrderItem> list;
}
