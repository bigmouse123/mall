package com.jiankun.mall.mapper;

import com.jiankun.mall.pojo.OperLog;
import com.jiankun.mall.pojo.query.OperLogQuery;

import java.util.List;

public interface OperLogMapper {
    public void add(OperLog operLog);

    List<OperLog> list(OperLogQuery operLogQuery);
}