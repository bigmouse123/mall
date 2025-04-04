package com.jiankun.mall.service;

import com.jiankun.mall.pojo.Cart;
import com.jiankun.mall.pojo.query.CartQuery;
import com.jiankun.mall.pojo.vo.CartVO;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/14 16:36
 */
public interface ICartService {
    void add(Cart cart);

    List<CartVO> list(CartQuery cartQuery);

    void update(Cart cart);

    void updateCheckedAll(Integer checked, Integer userId);

    void minus(Cart cart);

    void plus(Cart cart);

    void deleteById(Integer id);
}
