package com.jiankun.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiankun.mall.mapper.AdminMapper;
import com.jiankun.mall.pojo.Admin;
import com.jiankun.mall.pojo.query.AdminQuery;
import com.jiankun.mall.service.IAdminService;
import com.jiankun.mall.util.PageResult;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/7 19:11
 */
@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public PageResult<Admin> list(AdminQuery adminQuery) {
        PageHelper.startPage(adminQuery.getPage(), adminQuery.getLimit());
        List<Admin> list = adminMapper.list(adminQuery);
        PageInfo<Admin> pageInfo = new PageInfo<>(list);
        int count = (int) pageInfo.getTotal();
        return new PageResult<>(0, "", count, list);
    }

    @Override
    public void deleteById(Integer id) {
        adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteAll(int[] ids) {
        adminMapper.deleteAll(ids);
    }

    @Override
    public void add(Admin admin) {
        adminMapper.insertSelective(admin);
    }

    @Override
    public void update(Admin admin) {
        adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public Admin selectById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateStatus(Integer id, Integer status) {
        adminMapper.updateStatus(id, status);
    }

    @Override
    public Admin login(String name, String password) {
        return adminMapper.login(name, password);
    }

    @Override
    public Boolean register(Admin admin) {
        System.out.println(adminMapper.register(admin));
        return adminMapper.register(admin) > 0;
    }

    @Override
    public Admin getLoginAdmin(HttpServletRequest request) {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin == null) {
            admin = new Admin();
        }
        return admin;
    }
}
