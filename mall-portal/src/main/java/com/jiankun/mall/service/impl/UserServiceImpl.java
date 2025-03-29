package com.jiankun.mall.service.impl;

import com.jiankun.mall.mapper.UserMapper;
import com.jiankun.mall.pojo.User;
import com.jiankun.mall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/14 19:49
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String name, String password) {
        return userMapper.login(name, password);
    }

    @Override
    public Boolean register(User user) {
        System.out.println(userMapper.register(user));
        return userMapper.register(user) > 0;
    }

    @Override
    public void add(User user) {
        userMapper.insertSelective(user);
    }
}
