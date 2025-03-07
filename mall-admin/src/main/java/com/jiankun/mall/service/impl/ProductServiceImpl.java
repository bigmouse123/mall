package com.jiankun.mall.service.impl;

import com.jiankun.mall.service.IProductService;
import com.jiankun.mall.mapper.ProductMapper;
import com.jiankun.mall.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/7 15:01
 */
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> list() {
        return productMapper.list();
    }
}
