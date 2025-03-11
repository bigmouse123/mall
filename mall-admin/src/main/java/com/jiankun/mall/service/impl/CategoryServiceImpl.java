package com.jiankun.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiankun.mall.mapper.CategoryMapper;
import com.jiankun.mall.pojo.Category;
import com.jiankun.mall.pojo.query.CategoryQuery;
import com.jiankun.mall.service.ICategoryService;
import com.jiankun.mall.util.PageResult;
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

    @Override
    public PageResult<Category> list(CategoryQuery categotyQuery) {
        PageHelper.startPage(categotyQuery.getPage(), categotyQuery.getLimit());
        List<Category> list = categoryMapper.list(categotyQuery);
        PageInfo<Category> pageInfo = new PageInfo<>(list);
        int count = (int) pageInfo.getTotal();
        return new PageResult<>(0, "", count, list);
    }

    @Override
    public void add(Category category) {
        categoryMapper.insertSelective(category);
    }

    @Override
    public void deleteById(Integer id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteAll(int[] ids) {
        categoryMapper.deleteAll(ids);
    }
}
