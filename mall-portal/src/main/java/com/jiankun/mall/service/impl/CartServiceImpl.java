package com.jiankun.mall.service.impl;

import com.jiankun.mall.controller.CartController;
import com.jiankun.mall.mapper.CartMapper;
import com.jiankun.mall.pojo.Cart;
import com.jiankun.mall.pojo.vo.CartVO;
import com.jiankun.mall.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        int count = cartMapper.selectCountByUserIdAndProductId(cart);
        if (count == 0) {
            cartMapper.insertSelective(cart);
        } else {
            cartMapper.updateQuantity(cart);
        }
    }

    @Override
    public List<CartVO> list(Integer userId) {
        return cartMapper.list(userId);
    }

    @Override
    public void update(Cart cart) {
        cartMapper.updateByPrimaryKeySelective(cart);
    }

    @Override
    public void updateCheckedAll(Integer checked, Integer userId) {
        cartMapper.updateCheckedAll(checked, userId);
    }

    @Override
    public void minus(Cart cart) {
        cartMapper.minus(cart);
    }

    @Override
    public void plus(Cart cart) {
        cartMapper.plus(cart);
    }

    @Override
    public void deleteById(Integer id) {
        cartMapper.deleteByPrimaryKey(id);
    }
}
