package com.jiankun.mall.service;

import com.jiankun.mall.pojo.Admin;
import com.jiankun.mall.pojo.query.AdminQuery;
import com.jiankun.mall.util.PageResult;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/7 19:12
 */
public interface IAdminService {

    PageResult<Admin> list(AdminQuery adminQuery);

    void deleteById(Integer id);

    void deleteAll(int[] ids);

    void add(Admin admin);

    void update(Admin admin);

    Admin selectById(Integer id);

    void updateStatus(Integer id, Integer status);

    Admin login(String name, String password);

    Boolean register(Admin admin);

    Admin getLoginAdmin(HttpServletRequest request);
}
