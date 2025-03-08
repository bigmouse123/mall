package com.jiankun.mall.controller;

import com.jiankun.mall.pojo.Admin;
import com.jiankun.mall.pojo.query.AdminQuery;
import com.jiankun.mall.service.IAdminService;
import com.jiankun.mall.util.PageResult;
import com.jiankun.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/7 19:11
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IAdminService adminService;

    @RequestMapping("/list")
    public PageResult<Admin> list(AdminQuery adminQuery) {
        PageResult<Admin> pageResult = adminService.list(adminQuery);
        return pageResult;
    }

    @RequestMapping("/deleteById")
    public Result deleteById(Integer id) {
        adminService.deleteById(id);
        return Result.ok("删除成功");
    }

    @RequestMapping("/deleteAll")
    public Result deleteAll(int[] ids) {
        adminService.deleteAll(ids);
        return Result.ok("删除成功");
    }

    @RequestMapping("/add")
    public Result add(Admin admin) {
        adminService.add(admin);
        return Result.ok("添加成功");
    }

    @RequestMapping("/selectById")
    public Result selectById(Integer id) {
        Admin admin = adminService.selectById(id);
        return Result.ok(admin);
    }

    @RequestMapping("/update")
    public Result update(Admin admin) {
        adminService.update(admin);
        return Result.ok("更新成功");
    }
}
