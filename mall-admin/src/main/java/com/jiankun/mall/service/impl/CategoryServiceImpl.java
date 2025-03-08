package com.jiankun.mall.service.impl;

import com.jiankun.mall.mapper.CategoryMapper;
import com.jiankun.mall.pojo.Category;
import com.jiankun.mall.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/7 20:48
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> selectAll() {
        return categoryMapper.selectAll();
    }

    @Override
    public List<Category> selectAll1() {
        return categoryMapper.selectAll1();
    }

    @Override
    public List<Category> selectAll2(Integer id) {
        return categoryMapper.selectAll2(id);
    }

    @Override
    public Integer selectParentIdByCategoryId(Integer categoryId) {
        return categoryMapper.selectParentIdByCategoryId(categoryId);
    }
}
