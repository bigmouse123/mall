package com.jiankun.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiankun.mall.constant.RedisConstant;
import com.jiankun.mall.pojo.query.ProductQuery;
import com.jiankun.mall.pojo.query.SalesQuery;
import com.jiankun.mall.pojo.vo.ProductPriceVO;
import com.jiankun.mall.pojo.vo.ProductSalesVO;
import com.jiankun.mall.pojo.vo.ProductVO;
import com.jiankun.mall.service.IProductService;
import com.jiankun.mall.mapper.ProductMapper;
import com.jiankun.mall.pojo.Product;
import com.jiankun.mall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "product", key = "#root.methodName + ':' + #productQuery.page", sync = true)
    public PageResult<ProductVO> list(ProductQuery productQuery) {
        PageHelper.startPage(productQuery.getPage(), productQuery.getLimit());
        List<ProductVO> list = productMapper.list(productQuery);
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
    @CacheEvict(value = "product", allEntries = true)
    public void add(Product product) {
        productMapper.insertSelective(product);
        redisTemplate.opsForSet().add(RedisConstant.UPLOAD_IMAGE_TO_DB, product.getMainImage());
    }

    @Cacheable(value = "product", key = "#root.methodName + ':' + #id", sync = true)
    @Override
    public Product selectById(Integer id) {
        Product product = productMapper.selectByPrimaryKey(id);
        redisTemplate.opsForSet().add(RedisConstant.UPLOAD_IMAGE, product.getMainImage());
        redisTemplate.opsForSet().add(RedisConstant.UPLOAD_IMAGE_TO_DB, product.getMainImage());
        return product;
    }

//    @CacheEvict(value = "product", key = "'selectById:' + #product.id")
    @CacheEvict(value = "product", allEntries = true)
    @Override
    public void update(Product product, String oldImage) {
        System.out.println(product);
        productMapper.updateByPrimaryKeySelective(product);
        redisTemplate.opsForSet().remove(RedisConstant.UPLOAD_IMAGE_TO_DB, oldImage);
        redisTemplate.opsForSet().add(RedisConstant.UPLOAD_IMAGE_TO_DB, product.getMainImage());
    }

    @Override
    public void updateStatus(Integer id, Integer status) {
        productMapper.updateStatus(id, status);
    }

    @Override
    public List<ProductPriceVO> getAllPrice() {
        return productMapper.getAllPrice();
    }

    @Override
    public List<ProductSalesVO> getAllSales(SalesQuery salesQuery) {
        return productMapper.getAllSales(salesQuery);
    }
}
