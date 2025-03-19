package com.jiankun.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiankun.mall.mapper.ProductMapper;
import com.jiankun.mall.pojo.Product;
import com.jiankun.mall.pojo.query.ProductQuery;
import com.jiankun.mall.service.IProductService;
import com.jiankun.mall.util.PageResult;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/11 17:22
 */
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedissonClient redissonClient;

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
        Product product = (Product) redisTemplate.opsForValue().get("product:" + id);
        if (product == null) {
            redissonClient.getLock("product_lock_" + id).lock();
            try {
                product = (Product) redisTemplate.opsForValue().get("product:" + id);
                if (product == null) {
                    product = productMapper.selectByPrimaryKey(id);
                    if (product != null) {
                        redisTemplate.opsForValue().set("product:" + id, product, 1, TimeUnit.DAYS);
                    } else {
                        redisTemplate.opsForValue().set("product:" + id, new Product(), 1, TimeUnit.MINUTES);
                    }
                }
            } finally {
                redissonClient.getLock("product_lock_" + id).unlock();
            }
        }
        return product;
    }

    /*@Override
    public Product seleteById(Integer id) {
        Product product = (Product) redisTemplate.opsForValue().get("product:" + id);
        if (product == null) {
            synchronized (this) {
                product = (Product) redisTemplate.opsForValue().get("product:" + id);
                if (product == null) {
                    product = productMapper.selectByPrimaryKey(id);
                    if (product != null) {
                        redisTemplate.opsForValue().set("product:" + id, product, 1, TimeUnit.DAYS);
                    } else {
                        redisTemplate.opsForValue().set("product:" + id, new Product(), 1, TimeUnit.MINUTES);
                    }
                }
            }
        }
        return product;
    }*/
}
