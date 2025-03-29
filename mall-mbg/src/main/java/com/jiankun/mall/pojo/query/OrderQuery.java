package com.jiankun.mall.pojo.query;

import lombok.Data;

import java.util.Date;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/29 17:06
 */
@Data
public class OrderQuery {
    private Integer page;
    private Integer limit;
    private String userName;
    private Date beginCreateTime;
    private Date endCreateTime;
}
