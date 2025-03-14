package com.jiankun.mall.mapper;

import com.jiankun.mall.pojo.Category;
import com.jiankun.mall.pojo.query.CategoryQuery;
import com.jiankun.mall.pojo.vo.CategoryVO;

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

    List<Category> list(CategoryQuery categoryQuery);

    void deleteAll(int[] ids);

    List<CategoryVO> listAll();
}