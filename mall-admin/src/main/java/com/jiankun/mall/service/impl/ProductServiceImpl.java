package com.jiankun.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiankun.mall.constant.RedisConstant;
import com.jiankun.mall.pojo.query.ProductQuery;
import com.jiankun.mall.service.IProductService;
import com.jiankun.mall.mapper.ProductMapper;
import com.jiankun.mall.pojo.Product;
import com.jiankun.mall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public PageResult<Product> list(ProductQuery productQuery) {
        PageHelper.startPage(productQuery.getPage(), productQuery.getLimit());
        List<Product> list = productMapper.list(productQuery);
        PageInfo pageInfo = new PageInfo(list);
        int count = (int) pageInfo.getTotal();
        return new PageResult<>(0, "", count, list);
    }

    @Override
    public void deleteById(Integer id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteAll(int[] ids) {
        productMapper.deleteAll(ids);
    }

    @Override
    public void add(Product product) {
        productMapper.insertSelective(product);
        redisTemplate.opsForSet().add(RedisConstant.UPLOAD_IMAGE_TO_DB, product.getMainImage());
    }

    @Override
    public Product selectById(Integer id) {
        Product product = productMapper.selectByPrimaryKey(id);
        redisTemplate.opsForSet().add(RedisConstant.UPLOAD_IMAGE, product.getMainImage());
        redisTemplate.opsForSet().add(RedisConstant.UPLOAD_IMAGE_TO_DB, product.getMainImage());
        return product;
    }

    @Override
    public void update(Product product, String oldImage) {
        System.out.println(product);
        productMapper.updateByPrimaryKeySelective(product);
        redisTemplate.opsForSet().remove(RedisConstant.UPLOAD_IMAGE_TO_DB, oldImage);
        redisTemplate.opsForSet().add(RedisConstant.UPLOAD_IMAGE_TO_DB, product.getMainImage());
    }
}
