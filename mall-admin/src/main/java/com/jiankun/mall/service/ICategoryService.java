package com.jiankun.mall.service;

import com.jiankun.mall.pojo.Category;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/7 20:47
 */
public interface ICategoryService {
    List<Category> selectAll();

    List<Category> selectAll1();

    List<Category> selectAll2(Integer id);

    Integer selectParentIdByCategoryId(Integer categoryId);
}
