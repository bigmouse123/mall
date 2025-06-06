package com.jiankun.mall.controller;

import com.jiankun.mall.annotation.MyLog;
import com.jiankun.mall.pojo.Category;
import com.jiankun.mall.pojo.query.CategoryQuery;
import com.jiankun.mall.service.ICategoryService;
import com.jiankun.mall.util.PageResult;
import com.jiankun.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/7 20:46
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired

    ICategoryService categoryService;

    @RequestMapping("/selectAll")
    public Result<Category> selectAll() {
        List<Category> list = categoryService.selectAll();
        return Result.ok(list);
    }

    @RequestMapping("/selectAll1")
    public Result<Category> selectAll1() {
        List<Category> list = categoryService.selectAll1();
        return Result.ok(list);
    }

    @RequestMapping("/selectAll2")
    public Result<Category> selectAll2(Integer id) {
        List<Category> list = categoryService.selectAll2(id);
        return Result.ok(list);
    }

    @RequestMapping("/list")
    public PageResult<Category> list(CategoryQuery categoryQuery) {
        PageResult<Category> pageResult = categoryService.list(categoryQuery);
        return pageResult;
    }

    @MyLog(module = "分类添加")
    @RequestMapping("/add")
    public Result add(Category category) {
        categoryService.add(category);
        return Result.ok("添加成功");
    }

    @MyLog(module = "分类删除")
    @RequestMapping("/deleteById")
    public Result deleteById(Integer id) {
        categoryService.deleteById(id);
        return Result.ok("删除成功");
    }

    @MyLog(module = "分类删除")
    @RequestMapping("/deleteAll")
    public Result deleteAll(int[] ids) {
        categoryService.deleteAll(ids);
        return Result.ok("删除成功");
    }
}
