package com.jiankun.mall.service;

import com.jiankun.mall.pojo.Admin;
import com.jiankun.mall.pojo.query.AdminQuery;
import com.jiankun.mall.util.PageResult;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/7 19:12
 */
public interface IAdminService {

    PageResult<Admin> list(AdminQuery adminQuery);

    void deleteById(Integer id);

    void deleteAll(int[] ids);
}
