package com.jiankun.mall.service;

import com.jiankun.mall.pojo.Category;
import com.jiankun.mall.pojo.query.CategoryQuery;
import com.jiankun.mall.util.PageResult;

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

    PageResult<Category> list(CategoryQuery categoryQuery);

    void add(Category category);

    void deleteById(Integer id);

    void deleteAll(int[] ids);
}
