package com.jiankun.mall.service.impl;

import com.jiankun.mall.mapper.ShippingMapper;
import com.jiankun.mall.pojo.Shipping;
import com.jiankun.mall.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/17 8:46
 */
@Service
public class ShippingServiceImpl implements IShippingService {
    @Autowired
    private ShippingMapper shippingMapper;

    @Override
    public List<Shipping> list(Integer userId) {
        return shippingMapper.list(userId);
    }
}
