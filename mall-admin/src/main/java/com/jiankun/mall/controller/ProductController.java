package com.jiankun.mall.controller;

import com.jiankun.mall.pojo.Category;
import com.jiankun.mall.pojo.query.ProductQuery;
import com.jiankun.mall.service.ICategoryService;
import com.jiankun.mall.service.IProductService;
import com.jiankun.mall.util.PageResult;
import com.jiankun.mall.util.Result;
import com.jiankun.mall.pojo.Product;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private ICategoryService categoryService;

    @RequestMapping("/list")
    public PageResult<Product> list(ProductQuery productQuery) {
        PageResult<Product> pageResult = productService.list(productQuery);
        return pageResult;
    }

    @RequestMapping("/deleteById")
    public Result deleteById(Integer id) {
        productService.deleteById(id);
        return Result.ok("删除成功");
    }

    @RequestMapping("/deleteAll")
    public Result deleteAll(int[] ids) {
        productService.deleteAll(ids);
        return Result.ok("删除成功");
    }

    @RequestMapping("/add")
    public Result add(Product product) {
        productService.add(product);
        return Result.ok("添加成功");
    }

    @RequestMapping("/selectById")
    public Result selectById(Integer id, Integer categoryId) {
        Product product = productService.selectById(id);
        Integer parentId = categoryService.selectParentIdByCategoryId(categoryId);
        List<Category> list1 = categoryService.selectAll1();
        List<Category> list2 = categoryService.selectAll2(parentId);
        Map<String, Object> map = new HashMap<>();
        map.put("parentId", parentId);
        map.put("product", product);
        map.put("list1", list1);
        map.put("list2", list2);
        return Result.ok(map);
    }

    @RequestMapping("/update")
    public Result update(Product product, HttpServletRequest request) {
        String oldImage = request.getParameter("oldImage");
        productService.update(product, oldImage);
        return Result.ok("更新成功");
    }

    @RequestMapping("/updateStatus")
    public Result updateStatus(Integer id, Integer status) {
        productService.updateStatus(id, status);
        return Result.ok();
    }

}
