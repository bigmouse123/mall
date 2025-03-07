package com.jiankun.mall.pojo.query;

import lombok.Data;

import java.util.Date;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/7 17:04
 */
@Data
public class ProductQuery {
    private Integer page;
    private Integer limit;
    private String name;
    private Date beginCreateTime;
    private Date endCreateTime;
}
