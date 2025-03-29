package com.jiankun.mall.service;

import com.jiankun.mall.pojo.User;
import com.jiankun.mall.pojo.query.UserQuery;
import com.jiankun.mall.util.PageResult;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/7 19:12
 */
public interface IUserService {

    PageResult<User> list(UserQuery userQuery);

    void deleteById(Integer id);

    void deleteAll(int[] ids);

    void add(User user);

    void update(User user);

    User selectById(Integer id);

    void updateStatus(Integer id, Integer status);
}
