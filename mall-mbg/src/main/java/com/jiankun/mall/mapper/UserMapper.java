package com.jiankun.mall.mapper;

import com.jiankun.mall.pojo.User;
import com.jiankun.mall.pojo.query.UserQuery;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User login(String name, String password);

    List<User> list(UserQuery userQuery);

    void deleteAll(int[] ids);

    void updateStatus(Integer id, Integer status);

    int register(User user);
}