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
        session.setAttribute("user", user);
        return Result.ok("登录成功");
    }
}
