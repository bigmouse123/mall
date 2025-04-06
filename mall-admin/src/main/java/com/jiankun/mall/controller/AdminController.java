package com.jiankun.mall.controller;

import cn.hutool.extra.servlet.JakartaServletUtil;
import com.jiankun.mall.annotation.AuthCheck;
import com.jiankun.mall.annotation.MyLog;
import com.jiankun.mall.constant.AdminConstant;
import com.jiankun.mall.pojo.Admin;
import com.jiankun.mall.pojo.LoginLog;
import com.jiankun.mall.pojo.query.AdminQuery;
import com.jiankun.mall.service.IAdminService;
import com.jiankun.mall.service.ILoginLogService;
import com.jiankun.mall.util.PageResult;
import com.jiankun.mall.util.Result;
import jakarta.servlet.http.HttpServletRequest;
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
    @Autowired
    private ILoginLogService loginLogService;

    @RequestMapping("/list")
    @MyLog(module = "查看管理员")
    public PageResult<Admin> list(AdminQuery adminQuery) {
        PageResult<Admin> pageResult = adminService.list(adminQuery);
        return pageResult;
    }

    @RequestMapping("/deleteById")
    @MyLog(module = "管理员删除")
    @AuthCheck(mustRole = AdminConstant.SUPER_ADMIN_ROLE)
    public Result deleteById(Integer id) {
        adminService.deleteById(id);
        return Result.ok("删除成功");
    }

    @RequestMapping("/deleteAll")
    @MyLog(module = "管理员删除")
    @AuthCheck(mustRole = AdminConstant.SUPER_ADMIN_ROLE)
    public Result deleteAll(int[] ids) {
        adminService.deleteAll(ids);
        return Result.ok("删除成功");
    }

    @MyLog(module = "管理员添加")
    @AuthCheck(mustRole = AdminConstant.SUPER_ADMIN_ROLE)
    @RequestMapping("/add")
    public Result add(Admin admin) {
        adminService.add(admin);
        return Result.ok("添加成功");
    }

    @RequestMapping("/selectById")
    @MyLog
    public Result selectById(Integer id) {
        Admin admin = adminService.selectById(id);
        return Result.ok(admin);
    }

    @RequestMapping("/update")
    @MyLog(module = "管理员更新")
    @AuthCheck(mustRole = AdminConstant.SUPER_ADMIN_ROLE)
    public Result update(Admin admin) {
        adminService.update(admin);
        return Result.ok("更新成功");
    }

    @RequestMapping("/updateStatus")
    @MyLog(module = "管理员禁用/启用")
    @AuthCheck(mustRole = AdminConstant.SUPER_ADMIN_ROLE)
    public Result updateStatus(Integer id, Integer status) {
        adminService.updateStatus(id, status);
        return Result.ok();
    }

    @RequestMapping("/login")
    @MyLog(module = "管理员登录")
    public Result login(String name, String password, String code, HttpSession session, HttpServletRequest request) {
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

        LoginLog loginLog = new LoginLog();
        loginLog.setAdminName(name);
        loginLog.setAdminId(admin.getId());
        loginLog.setStatus(1);
        loginLog.setMsg("登录成功");
        loginLog.setIp(JakartaServletUtil.getClientIP(request));
        loginLogService.add(loginLog);

        session.setAttribute("admin", admin);
        return Result.ok("登录成功");
    }

    @RequestMapping("/logout")
    @MyLog(module = "管理员注销")
    public Result logout(HttpSession session) {
        session.removeAttribute("admin");
        return Result.ok("退出成功");
    }


    @RequestMapping("/register")
    @MyLog
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
    @MyLog(module = "修改密码")
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
