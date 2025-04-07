package com.jiankun.mall.mapper;

import com.jiankun.mall.pojo.Area;

import java.util.List;

public interface AreaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);

    List<Area> list(Integer cityId);
}