package com.jiankun.mall.service;

import com.jiankun.mall.pojo.Product;
import com.jiankun.mall.pojo.query.ProductQuery;
import com.jiankun.mall.util.PageResult;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/7 15:01
 */
public interface IProductService {

    PageResult<Product> list(ProductQuery productQuery);

    void deleteById(Integer id);

    void deleteAll(int[] ids);

    void add(Product product);

    Product selectById(Integer id);

    void update(Product product);
}
