package com.jiankun.mall.service;

import com.jiankun.mall.pojo.Shipping;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/17 8:45
 */
public interface IShippingService {
    List<Shipping> list(Integer userId);
}
