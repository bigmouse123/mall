package com.jiankun.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiankun.mall.mapper.OperLogMapper;
import com.jiankun.mall.pojo.OperLog;
import com.jiankun.mall.pojo.query.OperLogQuery;
import com.jiankun.mall.service.IOperLogService;
import com.jiankun.mall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/18 20:40
 */
@Service
public class OperLogServiceImpl implements IOperLogService {
    @Autowired
    private OperLogMapper operLogMapper;

    @Override
    @Async("logTaskExecutor")
    public void add(OperLog operLog) {
        System.out.println(operLog);
        operLogMapper.add(operLog);
    }

    @Override
    public PageResult<OperLog> list(OperLogQuery operLogQuery) {
        PageHelper.startPage(operLogQuery.getPage(), operLogQuery.getLimit());
        List<OperLog> list = operLogMapper.list(operLogQuery);
        PageInfo<OperLog> pageInfo = new PageInfo<>(list);
        int count = (int) pageInfo.getTotal();
        return new PageResult<>(0, "", count, list);
    }
}
