package com.jiankun.mall.service;

import com.jiankun.mall.pojo.User;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/14 19:49
 */
public interface IUserService {

    User login(String name, String password);
}
