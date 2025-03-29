package com.jiankun.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiankun.mall.mapper.UserMapper;
import com.jiankun.mall.pojo.User;
import com.jiankun.mall.pojo.query.UserQuery;
import com.jiankun.mall.service.IUserService;
import com.jiankun.mall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/7 19:11
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageResult<User> list(UserQuery userQuery) {
        PageHelper.startPage(userQuery.getPage(), userQuery.getLimit());
        List<User> list = userMapper.list(userQuery);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        int count = (int) pageInfo.getTotal();
        return new PageResult<>(0, "", count, list);
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteAll(int[] ids) {
        userMapper.deleteAll(ids);
    }

    @Override
    public void add(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateStatus(Integer id, Integer status) {
        userMapper.updateStatus(id, status);
    }
}
