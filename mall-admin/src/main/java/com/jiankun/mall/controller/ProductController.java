package com.jiankun.mall.controller;

import com.jiankun.mall.service.IProductService;
import com.jiankun.mall.util.Result;
import com.jiankun.mall.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/7 14:59
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @RequestMapping("/list")
    public Result list() {
        List<Product> list = productService.list();
        return Result.ok(list);
    }

}
