package com.jiankun.mall.service.impl;

import com.jiankun.mall.mapper.OperLogMapper;
import com.jiankun.mall.pojo.OperLog;
import com.jiankun.mall.service.IOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void add(OperLog operLog) {
        System.out.println(operLog);
        operLogMapper.add(operLog);
    }
}
