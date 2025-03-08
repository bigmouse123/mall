package com.jiankun.mall.mapper;

import com.jiankun.mall.pojo.Category;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<Category> selectAll();

    List<Category> selectAll1();

    List<Category> selectAll2(Integer id);

    Integer selectParentIdByCategoryId(Integer categoryId);
}