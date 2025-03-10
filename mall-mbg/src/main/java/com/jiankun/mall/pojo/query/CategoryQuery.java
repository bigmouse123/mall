package com.jiankun.mall.pojo.query;

import lombok.Data;

import java.util.Date;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/10 16:00
 */
@Data
public class CategoryQuery {

    private Integer page;

    private Integer limit;

    private Integer id;

    private Integer parentId;

    private Date beginCreateTime;

    private Date endCreateTime;
}
