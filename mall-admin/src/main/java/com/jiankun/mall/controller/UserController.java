package com.jiankun.mall.controller;

import com.jiankun.mall.annotation.AuthCheck;
import com.jiankun.mall.annotation.MyLog;
import com.jiankun.mall.constant.AdminConstant;
import com.jiankun.mall.pojo.User;
import com.jiankun.mall.pojo.query.UserQuery;
import com.jiankun.mall.service.IUserService;
import com.jiankun.mall.service.ILoginLogService;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/list")
    public PageResult<User> list(UserQuery userQuery) {
        PageResult<User> pageResult = userService.list(userQuery);
        return pageResult;
    }

    @MyLog(module = "用户删除")
    @RequestMapping("/deleteById")
    public Result deleteById(Integer id) {
        userService.deleteById(id);
        return Result.ok("删除成功");
    }

    @MyLog(module = "用户删除")
    @RequestMapping("/deleteAll")
    public Result deleteAll(int[] ids) {
        userService.deleteAll(ids);
        return Result.ok("删除成功");
    }

    @MyLog(module = "用户添加")
    @RequestMapping("/add")
    public Result add(User user) {
        userService.add(user);
        return Result.ok("添加成功");
    }

    @RequestMapping("/selectById")
    public Result selectById(Integer id) {
        User user = userService.selectById(id);
        return Result.ok(user);
    }

    @MyLog(module = "用户更新")
    @RequestMapping("/update")
    public Result update(User user) {
        userService.update(user);
        return Result.ok("更新成功");
    }

    @MyLog(module = "用户禁用/解禁")
    @RequestMapping("/updateStatus")
    public Result updateStatus(Integer id, Integer status) {
        userService.updateStatus(id, status);
        return Result.ok();
    }
}
