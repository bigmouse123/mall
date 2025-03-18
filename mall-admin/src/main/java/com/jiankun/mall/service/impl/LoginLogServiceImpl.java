package com.jiankun.mall.service.impl;

import com.jiankun.mall.mapper.LoginLogMapper;
import com.jiankun.mall.pojo.LoginLog;
import com.jiankun.mall.service.ILoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/18 21:14
 */
@Service
public class LoginLogServiceImpl implements ILoginLogService {
    @Autowired
    private LoginLogMapper loginMapper;

    @Override
    public void add(LoginLog loginLog) {
        System.out.println(loginLog);
        loginMapper.add(loginLog);
    }
}
