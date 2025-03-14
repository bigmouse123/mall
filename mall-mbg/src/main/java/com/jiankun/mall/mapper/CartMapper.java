package com.jiankun.mall.mapper;

import com.jiankun.mall.pojo.Cart;
import com.jiankun.mall.pojo.vo.CartVO;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    List<CartVO> list(Integer userId);

    int selectCountByUserIdAndProductId(Cart cart);

    void updateQuantity(Cart cart);
}