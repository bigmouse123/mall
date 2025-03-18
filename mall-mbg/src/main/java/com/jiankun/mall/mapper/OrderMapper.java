package com.jiankun.mall.mapper;

import com.jiankun.mall.pojo.Order;
import com.jiankun.mall.pojo.vo.OrderVO;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Long orderNo);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long orderNo);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<OrderVO> list(Integer userId);
}