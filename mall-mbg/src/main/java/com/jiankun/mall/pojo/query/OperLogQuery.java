package com.jiankun.mall.pojo.query;

import lombok.Data;

import java.util.Date;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/4/7 0:54
 */
@Data
public class OperLogQuery {
    private Integer page;
    private Integer limit;
    private Date beginCreateTime;
    private Date endCreateTime;
}
