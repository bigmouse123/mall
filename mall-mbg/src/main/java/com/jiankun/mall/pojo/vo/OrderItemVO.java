package com.jiankun.mall.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/29 17:01
 */
@Data
public class OrderItemVO {
    private Integer id;

    private String userName;

    private Long orderNo;

    private Integer productId;

    private String productName;

    private String productImage;

    private BigDecimal currentUnitPrice;

    private Integer quantity;

    private BigDecimal totalPrice;
    
    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
