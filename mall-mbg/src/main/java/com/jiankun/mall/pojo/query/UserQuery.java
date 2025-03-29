package com.jiankun.mall.pojo.query;

import lombok.Data;

import java.util.Date;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/7 19:15
 */
@Data
public class UserQuery {
    private Integer page;
    private Integer limit;
    private String name;
    private Date beginCreateTime;
    private Date endCreateTime;
}
