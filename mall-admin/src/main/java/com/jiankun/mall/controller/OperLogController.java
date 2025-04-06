package com.jiankun.mall.controller;

import com.jiankun.mall.pojo.OperLog;
import com.jiankun.mall.pojo.query.OperLogQuery;
import com.jiankun.mall.service.IOperLogService;
import com.jiankun.mall.util.PageResult;
import com.jiankun.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/4/7 0:51
 */
@RestController
@RequestMapping("/operlog")
public class OperLogController {
    @Autowired
    private IOperLogService operLogService;

    @RequestMapping("/list")
    public PageResult<OperLog> list(OperLogQuery operLogQuery) {
        PageResult<OperLog> pageResult = operLogService.list(operLogQuery);
        return pageResult;
    }
}
