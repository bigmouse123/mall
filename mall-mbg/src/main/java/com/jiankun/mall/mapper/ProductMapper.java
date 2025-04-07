package com.jiankun.mall.mapper;

import com.jiankun.mall.pojo.Product;
import com.jiankun.mall.pojo.query.ProductQuery;
import com.jiankun.mall.pojo.query.SalesQuery;
import com.jiankun.mall.pojo.vo.CategoryCountVO;
import com.jiankun.mall.pojo.vo.ProductPriceVO;
import com.jiankun.mall.pojo.vo.ProductSalesVO;
import com.jiankun.mall.pojo.vo.ProductVO;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<ProductVO> list(ProductQuery productQuery);

    void deleteAll(int[] ids);

    void updateStatus(Integer id, Integer status);

    List<CategoryCountVO> count();

    List<ProductPriceVO> getAllPrice();

    List<ProductSalesVO> getAllSales(SalesQuery salesQuery);

//    List<Product> listByCategoryId(ProductQuery productQuery);
}