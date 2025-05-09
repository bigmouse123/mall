package com.jiankun.mall.controller;

import com.jiankun.mall.annotation.MyLog;
import com.jiankun.mall.pojo.Category;
import com.jiankun.mall.pojo.query.ProductQuery;
import com.jiankun.mall.pojo.query.SalesQuery;
import com.jiankun.mall.pojo.vo.ProductPriceVO;
import com.jiankun.mall.pojo.vo.ProductSalesVO;
import com.jiankun.mall.pojo.vo.ProductVO;
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
    public PageResult<ProductVO> list(ProductQuery productQuery) {
        PageResult<ProductVO> pageResult = productService.list(productQuery);
        return pageResult;
    }

    @MyLog(module = "产品删除")
    @RequestMapping("/deleteById")
    public Result deleteById(Integer id) {
        productService.deleteById(id);
        return Result.ok("删除成功");
    }

    @MyLog(module = "产品删除")
    @RequestMapping("/deleteAll")
    public Result deleteAll(int[] ids) {
        productService.deleteAll(ids);
        return Result.ok("删除成功");
    }

    @RequestMapping("/add")
    @MyLog(module = "产品添加")
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

    @MyLog(module = "产品更新")
    @RequestMapping("/update")
    public Result update(Product product, HttpServletRequest request) {
        String oldImage = request.getParameter("oldImage");
        productService.update(product, oldImage);
        return Result.ok("更新成功");
    }

    @MyLog(module = "产品上架/下架")
    @RequestMapping("/updateStatus")
    public Result updateStatus(Integer id, Integer status) {
        productService.updateStatus(id, status);
        return Result.ok();
    }

    @RequestMapping("/price")
    public List<ProductPriceVO> getAllPrice() {
        List<ProductPriceVO> list = productService.getAllPrice();
        return list;
    }

    @RequestMapping("/sales")
    public List<ProductSalesVO> getAllSales(SalesQuery salesQuery) {
        List<ProductSalesVO> list = productService.getAllSales(salesQuery);
        return list;
    }

}
