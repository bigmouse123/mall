package com.jiankun.mall.controller;

import com.jiankun.mall.pojo.vo.CategoryVO;
import com.jiankun.mall.service.ICategoryService;
import com.jiankun.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/11 15:21
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;

    @RequestMapping("/listAll")
    public Result listAll() {
        List<CategoryVO> list = categoryService.listAll();
        return Result.ok(list);
    }
}
