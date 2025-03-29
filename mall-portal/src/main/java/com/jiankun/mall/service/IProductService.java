package com.jiankun.mall.service;

import com.jiankun.mall.pojo.Product;
import com.jiankun.mall.pojo.query.ProductQuery;
import com.jiankun.mall.pojo.vo.ProductVO;
import com.jiankun.mall.util.PageResult;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/11 17:21
 */
public interface IProductService {

    PageResult<ProductVO> list(ProductQuery productQuery);

    Product seleteById(Integer id);
}
