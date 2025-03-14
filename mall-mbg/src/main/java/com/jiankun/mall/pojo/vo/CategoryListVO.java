package com.jiankun.mall.pojo.vo;

import com.jiankun.mall.pojo.Category;
import lombok.Data;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/11 15:24
 */
@Data
public class CategoryListVO {
    private Integer id;
    private String name;
    private List<Category> childList;
}
