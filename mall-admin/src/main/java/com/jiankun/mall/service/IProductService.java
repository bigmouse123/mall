package com.jiankun.mall.service;

import com.jiankun.mall.pojo.Product;
import com.jiankun.mall.pojo.query.ProductQuery;
import com.jiankun.mall.pojo.query.SalesQuery;
import com.jiankun.mall.pojo.vo.ProductPriceVO;
import com.jiankun.mall.pojo.vo.ProductSalesVO;
import com.jiankun.mall.pojo.vo.ProductVO;
import com.jiankun.mall.util.PageResult;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/7 15:01
 */
public interface IProductService {

    PageResult<ProductVO> list(ProductQuery productQuery);

    void deleteById(Integer id);

    void deleteAll(int[] ids);

    void add(Product product);

    Product selectById(Integer id);

    void update(Product product, String oldImage);

    void updateStatus(Integer id, Integer status);

    List<ProductPriceVO> getAllPrice();

    List<ProductSalesVO> getAllSales(SalesQuery salesQuery);
}
