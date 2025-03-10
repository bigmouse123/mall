package com.jiankun.mall.controller;

import com.jiankun.mall.pojo.Admin;
import com.jiankun.mall.pojo.query.AdminQuery;
import com.jiankun.mall.service.IAdminService;
import com.jiankun.mall.util.PageResult;
import com.jiankun.mall.util.Result;
import jakarta.servlet.http.HttpSession;
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

    @RequestMapping("/updateStatus")
    public Result updateStatus(Integer id, Integer status) {
        adminService.updateStatus(id, status);
        return Result.ok();
    }

    @RequestMapping("/login")
    public Result login(String name, String password, String code, HttpSession session) {
        Admin admin = adminService.login(name, password);
        String codeInSession = (String) session.getAttribute("codeInSession");
        if (!codeInSession.equals(code)) {
            return Result.error("验证码错误");
        }
        if (admin == null) {
            return Result.error("用户名或密码错误");
        }
        if (admin.getStatus() == 0) {
            return Result.error("该用户被封禁");
        }
        session.setAttribute("admin", admin);
        return Result.ok("登录成功");
    }

    @RequestMapping("/logout")
    public Result logout(HttpSession session) {
        session.removeAttribute("admin");
        return Result.ok("退出成功");
    }

    @RequestMapping("/register")
    public Result register(Admin admin) {
        Boolean isRepeat = adminService.register(admin);
        if (isRepeat) {
            return Result.error("该用户名已存在");
        } else {
            adminService.add(admin);
            return Result.ok("注册成功");
        }
    }

    @RequestMapping("/updatePassword")
    public Result updatePassword(String oldPassword, String newPassword, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (!oldPassword.equals(admin.getPassword())) {
            return Result.error("密码错误");
        }
        admin.setPassword(newPassword);
        adminService.update(admin);
        return Result.ok("修改成功");
    }
}
