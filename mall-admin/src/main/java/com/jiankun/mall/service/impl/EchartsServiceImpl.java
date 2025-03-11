package com.jiankun.mall.service.impl;

import com.jiankun.mall.mapper.ProductMapper;
import com.jiankun.mall.pojo.vo.CategoryCountVO;
import com.jiankun.mall.service.IEchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/11 8:55
 */
@Service
public class EchartsServiceImpl implements IEchartsService {
    @Autowired
    ProductMapper productMapper;

    @Override
    public List<CategoryCountVO> echarts() {
        return productMapper.count();
    }
}
