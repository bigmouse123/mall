package com.jiankun.mall.controller;

import com.jiankun.mall.pojo.User;
import com.jiankun.mall.service.IUserService;
import com.jiankun.mall.util.Result;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/14 19:46
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/login")
    public Result login(String name, String password, HttpSession session) {
        User user = userService.login(name, password);
        if (user == null) {
            return Result.error("用户名或密码错误");
        }
        if (user.getStatus() == 0) {
            return Result.error("该用户被封禁");
        }
        session.setAttribute("user", user);
        return Result.ok("登录成功");
    }

    @RequestMapping("/logout")
    public Result logout(HttpSession session) {
        session.removeAttribute("user");
        return Result.ok("退出成功");
    }

    @RequestMapping("/register")
    public Result register(User user, String passwordCheck) {
        if (!passwordCheck.equals(user.getPassword())) {
            return Result.error("两次密码不一致");
        }
        Boolean isRepeat = userService.register(user);
        if (isRepeat) {
            return Result.error("该用户名已存在");
        } else {
            userService.add(user);
            return Result.ok("注册成功");
        }
    }

    @RequestMapping("/resetPassword")
    public Result resetPassword(String oldPassword, String newPassword, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (!user.getPassword().equals(oldPassword)) {
            return Result.error("密码错误");
        }
        user.setPassword(newPassword);
        userService.update(user);
        return Result.ok("更新成功");
    }

    @RequestMapping("/update")
    public Result update(User user) {
        userService.update(user);
        return Result.ok("更新成功");
    }

    @RequestMapping("/selectById")
    public Result selectById(Integer id) {
        User user = userService.selectById(id);
        return Result.ok(user);
    }
}
