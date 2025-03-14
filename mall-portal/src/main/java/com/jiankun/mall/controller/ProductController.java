package com.jiankun.mall.controller;

import com.jiankun.mall.pojo.Product;
import com.jiankun.mall.pojo.query.ProductQuery;
import com.jiankun.mall.service.IProductService;
import com.jiankun.mall.util.PageResult;
import com.jiankun.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/11 17:20
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @RequestMapping("/list")
    public PageResult<Product> list(ProductQuery productQuery) {
        PageResult<Product> pageResult = productService.list(productQuery);
        return pageResult;
    }

    @RequestMapping("/selectById")
    public Result selectById(Integer id) {
        Product product = productService.seleteById(id);
        return Result.ok(product);
    }
}
