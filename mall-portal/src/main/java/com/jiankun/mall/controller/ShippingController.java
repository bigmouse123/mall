package com.jiankun.mall.controller;

import com.jiankun.mall.pojo.*;
import com.jiankun.mall.service.IShippingService;
import com.jiankun.mall.util.Result;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/17 8:44
 */
@RestController
@RequestMapping("/shipping")
public class ShippingController {
    @Autowired
    private IShippingService shippingService;

    @RequestMapping("/list")
    public Result list(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Shipping> list = shippingService.list(user.getId());
        return Result.ok(list);
    }

    @RequestMapping("/selectProvince")
    public Result selectProvince() {
        List<Province> list = shippingService.selectProvince();
        return Result.ok(list);
    }

    @RequestMapping("/selectCity")
    public Result selectCity(Integer provinceId) {
        List<City> list = shippingService.selectCity(provinceId);
        return Result.ok(list);
    }

    @RequestMapping("/selectArea")
    public Result selectArea(Integer cityId) {
        List<Area> list = shippingService.selectArea(cityId);
        return Result.ok(list);
    }

    @RequestMapping("/add")
    public Result add(Shipping shipping, HttpSession session) {
        User user = (User) session.getAttribute("user");
        shipping.setUserId(user.getId());
        shippingService.add(shipping);
        return Result.ok("添加成功");
    }

    @RequestMapping("/delete")
    public Result delete(Integer id) {
        shippingService.delete(id);
        return Result.ok("删除成功");
    }
}
