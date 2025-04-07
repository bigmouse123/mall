package com.jiankun.mall.service;

import com.jiankun.mall.pojo.Area;
import com.jiankun.mall.pojo.City;
import com.jiankun.mall.pojo.Province;
import com.jiankun.mall.pojo.Shipping;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/17 8:45
 */
public interface IShippingService {
    List<Shipping> list(Integer userId);

    List<Province> selectProvince();

    List<City> selectCity(Integer provinceId);

    List<Area> selectArea(Integer cityId);

    void add(Shipping shipping);

    void delete(Integer id);
}
