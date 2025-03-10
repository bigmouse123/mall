package com.jiankun.mall.mapper;

import com.jiankun.mall.pojo.Admin;
import com.jiankun.mall.pojo.query.AdminQuery;

import java.util.List;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    List<Admin> list(AdminQuery adminQuerys);

    void deleteAll(int[] ids);

    void updateStatus(Integer id, Integer status);

    Admin login(String name, String password);

    int register(Admin admin);
}