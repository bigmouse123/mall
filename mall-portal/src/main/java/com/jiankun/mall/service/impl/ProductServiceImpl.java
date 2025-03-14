package com.jiankun.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiankun.mall.mapper.ProductMapper;
import com.jiankun.mall.pojo.Product;
import com.jiankun.mall.pojo.query.ProductQuery;
import com.jiankun.mall.service.IProductService;
import com.jiankun.mall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/11 17:22
 */
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageResult<Product> list(ProductQuery productQuery) {
        PageHelper.startPage(productQuery.getPage(), productQuery.getLimit());
        List<Product> list = productMapper.list(productQuery);
        PageInfo pageInfo = new PageInfo(list);
        int count = (int) pageInfo.getTotal();
        return new PageResult<>(0, "", count, list);
    }

    @Override
    public Product seleteById(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }
}
