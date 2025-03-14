package com.jiankun.mall.service.impl;

import com.jiankun.mall.mapper.CategoryMapper;
import com.jiankun.mall.pojo.vo.CategoryVO;
import com.jiankun.mall.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/11 15:22
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryVO> listAll() {
        return categoryMapper.listAll();
    }
}
