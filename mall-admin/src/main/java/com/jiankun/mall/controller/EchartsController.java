package com.jiankun.mall.controller;

import com.jiankun.mall.pojo.query.ProductQuery;
import com.jiankun.mall.pojo.vo.CategoryCountVO;
import com.jiankun.mall.service.IEchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/11 8:52
 */
@Controller
public class EchartsController {

    @Autowired
    IEchartsService echartsService;

    @RequestMapping("/echarts")
    @ResponseBody
    public List echarts() {
        List<CategoryCountVO> list = echartsService.echarts();
        return list;
    }
}
