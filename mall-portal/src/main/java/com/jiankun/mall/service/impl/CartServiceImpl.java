package com.jiankun.mall.service.impl;

import com.jiankun.mall.controller.CartController;
import com.jiankun.mall.mapper.CartMapper;
import com.jiankun.mall.pojo.Cart;
import com.jiankun.mall.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/14 16:37
 */
@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartMapper cartMapper;

    @Override
    public void add(Cart cart) {
        cartMapper.insertSelective(cart);
    }
}
