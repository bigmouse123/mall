package com.jiankun.mall.service;

import com.jiankun.mall.pojo.OperLog;
import com.jiankun.mall.pojo.query.OperLogQuery;
import com.jiankun.mall.util.PageResult;

import java.awt.*;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/18 20:39
 */
public interface IOperLogService {
    public void add(OperLog operLog);

    PageResult<OperLog> list(OperLogQuery operLogQuery);
}
